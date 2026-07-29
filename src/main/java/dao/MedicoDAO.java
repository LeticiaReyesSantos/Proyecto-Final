package dao;

import java.sql.*;

public class MedicoDAO {

    public boolean esMedico(String codigoPersona) {
        String sql = "SELECT 1 FROM MEDICO WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigoPersona);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isActivo(String codigoPersona) {
        String sql = "SELECT activo FROM MEDICO WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigoPersona);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getBoolean("activo");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}