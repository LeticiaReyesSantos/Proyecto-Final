package dao;

import logico.Diagnostico;
import logico.Enfermedad;
import java.sql.*;
import java.util.ArrayList;

public class DiagnosticoDAO {

    public boolean insertar(Diagnostico d) {
        String sql = "INSERT INTO DIAGNOSTICO (codigo, fecha, tratamiento) VALUES (?, ?, ?)";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, d.getCodigo());
            stmt.setDate(2, Date.valueOf(d.getFecha()));
            stmt.setString(3, d.getTratamiento());
            stmt.executeUpdate();

            guardarSintomas(d.getCodigo(), d.getSintomas());
            guardarEnfermedades(d.getCodigo(), d.getEnfDiagnosticadas());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Diagnostico buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM DIAGNOSTICO WHERE codigo = ?";
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

    private void guardarSintomas(String codigoDiagnostico, ArrayList<String> sintomas) throws SQLException {
        if (sintomas == null) return;
        SintomaDAO sintomaDAO = new SintomaDAO();
        String sql = "INSERT INTO DIAGNOSTICO_SINTOMA (codigo_diagnostico, codigo_sintoma) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            for (String nombreSintoma : sintomas) {
                String codigoSintoma = sintomaDAO.obtenerOCrear(nombreSintoma);
                stmt.setString(1, codigoDiagnostico);
                stmt.setString(2, codigoSintoma);
                stmt.executeUpdate();
            }
        }
    }

    private void guardarEnfermedades(String codigoDiagnostico, ArrayList<Enfermedad> enfermedades) throws SQLException {
        if (enfermedades == null) return;
    }

    private Diagnostico mapear(ResultSet rs) throws SQLException {
        String codigo = rs.getString("codigo");
        ArrayList<String> sintomas = new SintomaDAO().listarNombresPorDiagnostico(codigo);
        Diagnostico d = new Diagnostico(codigo, rs.getDate("fecha").toLocalDate(), sintomas, rs.getString("tratamiento"));
        return d;
    }
}