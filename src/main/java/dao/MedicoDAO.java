package dao;

import logico.Medico;
import java.sql.*;

public class MedicoDAO {

    public boolean insertar(Medico m) {
        String sql = "INSERT INTO MEDICO (codigo, especialidad, max_citas, activo) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, m.getCodigo());
            stmt.setString(2, m.getEspecialidad());
            stmt.setInt(3, m.getMaxCitas());
            stmt.setBoolean(4, m.isActivo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(String codigo, String especialidad, int maxCitas) {
        String sql = "UPDATE MEDICO SET especialidad = ?, max_citas = ? WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, especialidad);
            stmt.setInt(2, maxCitas);
            stmt.setString(3, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean desactivar(String codigo) {
        String sql = "UPDATE MEDICO SET activo = false WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean esMedico(String codigo) {
        String sql = "SELECT 1 FROM MEDICO WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isActivo(String codigo) {
        String sql = "SELECT activo FROM MEDICO WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getBoolean("activo");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}