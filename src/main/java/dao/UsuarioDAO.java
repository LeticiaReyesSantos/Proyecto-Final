package dao;

import java.sql.*;

public class UsuarioDAO {

    public String autenticar(String nombreUsuario, String pass) {
        String sql = "SELECT codigo_persona FROM USUARIO WHERE nombre_usuario = ? AND contrasena = ?";

        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("codigo_persona");
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertar(String nombreUsuario, String contrasena, String tipo, String codigoPersona) {
        String sql = "INSERT INTO USUARIO (nombre_usuario, contrasena, tipo, codigo_persona) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasena);
            stmt.setString(3, tipo);
            stmt.setString(4, codigoPersona);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}