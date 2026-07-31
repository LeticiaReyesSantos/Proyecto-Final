package dao;

import java.sql.*;
import java.util.ArrayList;

public class SintomaDAO {

    public String obtenerOCrear(String nombre) {
        String sqlBuscar = "SELECT codigo FROM SINTOMA WHERE nombre = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sqlBuscar)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("codigo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String nuevoCodigo = "S-" + System.currentTimeMillis(); // simple, evita choques
        String sqlInsertar = "INSERT INTO SINTOMA (codigo, nombre) VALUES (?, ?)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sqlInsertar)) {
            stmt.setString(1, nuevoCodigo);
            stmt.setString(2, nombre);
            stmt.executeUpdate();
            return nuevoCodigo;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> listarNombresPorEnfermedad(String codigoEnfermedad) {
        ArrayList<String> lista = new ArrayList<>();
        String sql = "SELECT s.nombre FROM SINTOMA s " +
                "JOIN ENFERMEDAD_SINTOMA es ON s.codigo = es.codigo_sintoma " +
                "WHERE es.codigo_enfermedad = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigoEnfermedad);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<String> listarNombresPorDiagnostico(String codigoDiagnostico) {
        ArrayList<String> lista = new ArrayList<>();
        String sql = "SELECT s.nombre FROM SINTOMA s " +
                "JOIN DIAGNOSTICO_SINTOMA ds ON s.codigo = ds.codigo_sintoma " +
                "WHERE ds.codigo_diagnostico = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigoDiagnostico);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}