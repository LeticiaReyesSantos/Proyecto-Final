package dao;

import logico.Enfermedad;
import java.sql.*;
import java.util.ArrayList;

public class EnfermedadDAO {

    public boolean insertar(Enfermedad e) {
        String sql = "INSERT INTO ENFERMEDAD (codigo, nombre, tratamiento, tipo, controlada) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, e.getCodigo());
            stmt.setString(2, e.getNombre());
            stmt.setString(3, e.getTratamiento());
            stmt.setString(4, e.getTipo());
            stmt.setBoolean(5, e.isControlada());
            stmt.executeUpdate();

            guardarSintomas(e.getCodigo(), e.getSintomas());
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(String codigo, String tratamiento, boolean controlada, ArrayList<String> sintomas) {
        String sql = "UPDATE ENFERMEDAD SET tratamiento = ?, controlada = ? WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tratamiento);
            stmt.setBoolean(2, controlada);
            stmt.setString(3, codigo);
            stmt.executeUpdate();

            // Limpiar y regrabar relación de síntomas
            String sqlDelete = "DELETE FROM ENFERMEDAD_SINTOMA WHERE codigo_enfermedad = ?";
            try (PreparedStatement del = con.prepareStatement(sqlDelete)) {
                del.setString(1, codigo);
                del.executeUpdate();
            }
            guardarSintomas(codigo, sintomas);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean marcarControlada(String codigo) {
        String sql = "UPDATE ENFERMEDAD SET controlada = true WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        String check = "SELECT 1 FROM PACIENTE_ENFERMEDAD WHERE codigo_enfermedad = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmtCheck = con.prepareStatement(check)) {
            stmtCheck.setString(1, codigo);
            if (stmtCheck.executeQuery().next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String sql = "DELETE FROM ENFERMEDAD WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Enfermedad buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM ENFERMEDAD WHERE codigo = ?";
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

    public ArrayList<Enfermedad> listarTodas() {
        ArrayList<Enfermedad> lista = new ArrayList<>();
        String sql = "SELECT * FROM ENFERMEDAD";
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

    public ArrayList<Enfermedad> listarControladas(boolean controlada) {
        ArrayList<Enfermedad> lista = new ArrayList<>();
        String sql = "SELECT * FROM ENFERMEDAD WHERE controlada = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setBoolean(1, controlada);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private void guardarSintomas(String codigoEnfermedad, ArrayList<String> sintomas) throws SQLException {
        if (sintomas == null) return;
        SintomaDAO sintomaDAO = new SintomaDAO();
        String sql = "INSERT INTO ENFERMEDAD_SINTOMA (codigo_enfermedad, codigo_sintoma) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            for (String nombreSintoma : sintomas) {
                String codigoSintoma = sintomaDAO.obtenerOCrear(nombreSintoma);
                stmt.setString(1, codigoEnfermedad);
                stmt.setString(2, codigoSintoma);
                stmt.executeUpdate();
            }
        }
    }

    private Enfermedad mapear(ResultSet rs) throws SQLException {
        Enfermedad e = new Enfermedad(
                rs.getString("codigo"),
                rs.getString("nombre"),
                rs.getString("tratamiento"),
                rs.getString("tipo"),
                rs.getBoolean("controlada")
        );
        e.setSintomas(new SintomaDAO().listarNombresPorEnfermedad(e.getCodigo()));
        return e;
    }
}