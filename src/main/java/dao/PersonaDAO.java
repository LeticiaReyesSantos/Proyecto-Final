package dao;

import logico.Persona;
import logico.User;

import java.sql.*;
import java.time.LocalDate;

public class PersonaDAO {

    public boolean insertar(Persona p) {
        String sql = "INSERT INTO PERSONA (codigo, cedula, nombres, apellidos, fecha_nacimiento, genero, telefono, direccion, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, p.getCodigo());
            stmt.setString(2, p.getCedula());
            stmt.setString(3, p.getNombres());
            stmt.setString(4, p.getApellidos());
            stmt.setDate(5, Date.valueOf(p.getFechaNacimiento()));
            stmt.setString(6, String.valueOf(p.getGenero()));
            stmt.setString(7, p.getTelefono());
            stmt.setString(8, p.getDireccion());
            stmt.setString(9, p.getEmail());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Persona buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM PERSONA WHERE codigo = ?";

        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearPersona(rs);
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean cedulaUnica(String cedula) {
        String sql = "SELECT COUNT(*) FROM PERSONA WHERE cedula = ?";

        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) == 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Persona mapearPersona(ResultSet rs) throws SQLException {
        Persona p = new Persona(
                rs.getString("codigo"),
                rs.getString("cedula"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getDate("fecha_nacimiento").toLocalDate(),
                rs.getString("genero").charAt(0),
                rs.getString("telefono"),
                rs.getString("direccion"),
                rs.getString("email"),
                null
        );

        String sqlUser = "SELECT nombre_usuario, contrasena, tipo FROM USUARIO WHERE codigo_persona = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sqlUser)) {
            stmt.setString(1, p.getCodigo());
            ResultSet rsUser = stmt.executeQuery();
            if (rsUser.next()) {
                User u = new User(rsUser.getString("tipo"), rsUser.getString("nombre_usuario"), rsUser.getString("contrasena"));
                p.setUser(u);
            }
        }

        return p;
    }
}