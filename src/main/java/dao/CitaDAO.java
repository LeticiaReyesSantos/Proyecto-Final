package dao;

import logico.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CitaDAO {

    public boolean insertar(Cita c) {
        String sql = "INSERT INTO CITA (codigo, codigo_persona, codigo_medico, fecha, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, c.getCodigo());
            stmt.setString(2, c.getPersona().getCodigo());
            stmt.setString(3, c.getMedico().getCodigo());
            stmt.setDate(4, Date.valueOf(c.getFecha()));
            stmt.setBoolean(5, c.isEstado());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarEstado(String codigo, boolean estado) {
        String sql = "UPDATE CITA SET estado = ? WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setBoolean(1, estado);
            stmt.setString(2, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarFecha(String codigo, LocalDate fecha) {
        String sql = "UPDATE CITA SET fecha = ? WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setString(2, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM CITA WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cita buscarPorCodigo(String codigo) {
        // Si ya está convertida a Consulta, no la devolvemos como Cita simple
        String sqlConsulta = "SELECT 1 FROM CONSULTA WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmtC = con.prepareStatement(sqlConsulta)) {
            stmtC.setString(1, codigo);
            if (stmtC.executeQuery().next()) {
                return null; // ya es Consulta, se busca con ConsultaDAO
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM CITA WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Cita> listarTodas() {
        ArrayList<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM CITA WHERE codigo NOT IN (SELECT codigo FROM CONSULTA)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Cita> listarPorMedico(String codigoMedico) {
        ArrayList<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM CITA WHERE codigo_medico = ? AND codigo NOT IN (SELECT codigo FROM CONSULTA)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigoMedico);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int cantCitasDia(String codigoMedico, LocalDate fecha) {
        String sql = "SELECT COUNT(*) FROM CITA WHERE codigo_medico = ? AND fecha = ? AND estado = false";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigoMedico);
            stmt.setDate(2, Date.valueOf(fecha));
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<Cita> listarPorPersonaOMedico(String codigo, LocalDate fecha) {
        ArrayList<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM CITA WHERE (codigo_persona = ? OR codigo_medico = ?) " +
                "AND fecha = ? AND codigo NOT IN (SELECT codigo FROM CONSULTA)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.setString(2, codigo);
            stmt.setDate(3, Date.valueOf(fecha));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Cita> listarPendientesPorMedico(String codigoMedico) {
        ArrayList<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM CITA WHERE codigo_medico = ? AND estado = false " +
                "AND fecha >= CURRENT_DATE AND codigo NOT IN (SELECT codigo FROM CONSULTA)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigoMedico);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Cita mapear(ResultSet rs) throws SQLException {
        PersonaDAO personaDAO = new PersonaDAO();
        Persona persona = personaDAO.buscarPersonaCompleta(rs.getString("codigo_persona"));
        Persona medicoPersona = personaDAO.buscarPorCodigo(rs.getString("codigo_medico"));

        Medico medico = new Medico(
                medicoPersona.getCodigo(), medicoPersona.getCedula(), medicoPersona.getNombres(),
                medicoPersona.getApellidos(), medicoPersona.getFechaNacimiento(), medicoPersona.getGenero(),
                medicoPersona.getTelefono(), medicoPersona.getDireccion(), medicoPersona.getEmail(),
                "", 0, medicoPersona.getUser()
        );

        Cita c = new Cita(rs.getString("codigo"), persona, medico, rs.getDate("fecha").toLocalDate());
        c.setEstado(rs.getBoolean("estado"));
        return c;
    }
}