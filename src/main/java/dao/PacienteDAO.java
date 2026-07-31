package dao;

import logico.Paciente;
import java.sql.*;

public class PacienteDAO {

    public boolean insertar(Paciente p) {
        String sql = "INSERT INTO PACIENTE (codigo, tipo_sangre) VALUES (?, ?)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, p.getCodigo());
            stmt.setString(2, p.getTipoSangre());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean esPaciente(String codigo) {
        String sql = "SELECT 1 FROM PACIENTE WHERE codigo = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}