package dao;

import logico.Vacuna;
import logico.Enfermedad;
import java.sql.*;
import java.util.ArrayList;

public class VacunaDAO {

    public boolean insertar(Vacuna v) {
        String sql = "INSERT INTO VACUNA (codigo, nombre, descripcion, codigo_enfermedad) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, v.getCodigo());
            stmt.setString(2, v.getNombre());
            stmt.setString(3, v.getDescripcion());
            stmt.setString(4, v.getEnfermedad().getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarDescripcion(String codigo, String descripcion) {
        String sql = "UPDATE VACUNA SET descripcion = ? WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, descripcion);
            stmt.setString(2, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        String check = "SELECT 1 FROM PACIENTE_VACUNA WHERE codigo_vacuna = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmtCheck = con.prepareStatement(check)) {
            stmtCheck.setString(1, codigo);
            if (stmtCheck.executeQuery().next()) return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String sql = "DELETE FROM VACUNA WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Vacuna buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM VACUNA WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapear(rs);
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Vacuna> listarTodas() {
        ArrayList<Vacuna> lista = new ArrayList<>();
        String sql = "SELECT * FROM VACUNA";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int contarTotal() {
        String sql = "SELECT COUNT(*) FROM VACUNA";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Vacuna mapear(ResultSet rs) throws SQLException {
        Enfermedad enf = new EnfermedadDAO().buscarPorCodigo(rs.getString("codigo_enfermedad"));
        Vacuna v = new Vacuna(rs.getString("codigo"), rs.getString("nombre"), enf, rs.getString("descripcion"));
        return v;
    }
}