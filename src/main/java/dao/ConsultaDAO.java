package dao;

import logico.*;
import java.sql.*;
import java.util.ArrayList;

public class ConsultaDAO {

    public boolean crearConsulta(String codigoCita, Double precio, ArrayList<String> sintomas,
                                 String tratamiento, ArrayList<Enfermedad> enfermedades,
                                 Vacuna vacAplicada, String codigoPaciente, String codigoDiagnostico) {

        try (Connection con = ConnectionDB.obtenerConexion()) {
            con.setAutoCommit(false);

            try {
                String sqlDiag = "INSERT INTO DIAGNOSTICO (codigo, fecha, tratamiento) VALUES (?, CURRENT_DATE, ?)";
                try (PreparedStatement stmt = con.prepareStatement(sqlDiag)) {
                    stmt.setString(1, codigoDiagnostico);
                    stmt.setString(2, tratamiento);
                    stmt.executeUpdate();
                }

                SintomaDAO sintomaDAO = new SintomaDAO();
                if (sintomas != null) {
                    String sqlDS = "INSERT INTO DIAGNOSTICO_SINTOMA (codigo_diagnostico, codigo_sintoma) VALUES (?, ?) ON CONFLICT DO NOTHING";
                    try (PreparedStatement stmt = con.prepareStatement(sqlDS)) {
                        for (String nombre : sintomas) {
                            String codSintoma = sintomaDAO.obtenerOCrear(nombre);
                            stmt.setString(1, codigoDiagnostico);
                            stmt.setString(2, codSintoma);
                            stmt.executeUpdate();
                        }
                    }
                }

                String sqlConsulta = "INSERT INTO CONSULTA (codigo, precio, visibilidad, codigo_diagnostico) VALUES (?, ?, false, ?)";
                try (PreparedStatement stmt = con.prepareStatement(sqlConsulta)) {
                    stmt.setString(1, codigoCita);
                    stmt.setDouble(2, precio);
                    stmt.setString(3, codigoDiagnostico);
                    stmt.executeUpdate();
                }

                String sqlEstado = "UPDATE CITA SET estado = true WHERE codigo = ?";
                try (PreparedStatement stmt = con.prepareStatement(sqlEstado)) {
                    stmt.setString(1, codigoCita);
                    stmt.executeUpdate();
                }

                if (enfermedades != null) {
                    String sqlPE = "INSERT INTO PACIENTE_ENFERMEDAD (codigo_paciente, codigo_enfermedad) VALUES (?, ?) ON CONFLICT DO NOTHING";
                    try (PreparedStatement stmt = con.prepareStatement(sqlPE)) {
                        for (Enfermedad enf : enfermedades) {
                            stmt.setString(1, codigoPaciente);
                            stmt.setString(2, enf.getCodigo());
                            stmt.executeUpdate();
                        }
                    }
                }

                if (vacAplicada != null) {
                    String sqlPV = "INSERT INTO PACIENTE_VACUNA (codigo_paciente, codigo_vacuna, aplicada, fecha) " +
                            "VALUES (?, ?, true, CURRENT_DATE) " +
                            "ON CONFLICT (codigo_paciente, codigo_vacuna) DO UPDATE SET aplicada = true, fecha = CURRENT_DATE";
                    try (PreparedStatement stmt = con.prepareStatement(sqlPV)) {
                        stmt.setString(1, codigoPaciente);
                        stmt.setString(2, vacAplicada.getCodigo());
                        stmt.executeUpdate();
                    }
                }

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Consulta> listarTodas() {
        ArrayList<Consulta> lista = new ArrayList<>();
        String sql = "SELECT c.codigo, c.precio, c.visibilidad, c.codigo_diagnostico, " +
                "ci.codigo_persona, ci.codigo_medico, ci.fecha, ci.estado " +
                "FROM CONSULTA c JOIN CITA ci ON c.codigo = ci.codigo";
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

    private Consulta mapear(ResultSet rs) throws SQLException {
        PersonaDAO personaDAO = new PersonaDAO();
        Persona persona = personaDAO.buscarPersonaCompleta(rs.getString("codigo_persona"));
        Persona medicoPersona = personaDAO.buscarPorCodigo(rs.getString("codigo_medico"));

        Medico medico = new Medico(medicoPersona.getCodigo(), medicoPersona.getCedula(), medicoPersona.getNombres(),
                medicoPersona.getApellidos(), medicoPersona.getFechaNacimiento(), medicoPersona.getGenero(),
                medicoPersona.getTelefono(), medicoPersona.getDireccion(), medicoPersona.getEmail(),
                "", 0, medicoPersona.getUser());

        Diagnostico diag = new DiagnosticoDAO().buscarPorCodigo(rs.getString("codigo_diagnostico"));

        Consulta c = new Consulta(rs.getString("codigo"), persona, medico, rs.getDate("fecha").toLocalDate(),
                rs.getDouble("precio"), null, rs.getBoolean("visibilidad"), diag);

        c.setEstado(rs.getBoolean("estado"));
        return c;
    }


    public ArrayList<Consulta> listarPorPaciente(String codigoPaciente) {
        ArrayList<Consulta> lista = new ArrayList<>();
        String sql = "SELECT c.codigo, c.precio, c.visibilidad, c.codigo_diagnostico, " +
                "ci.codigo_persona, ci.codigo_medico, ci.fecha, ci.estado " +
                "FROM CONSULTA c JOIN CITA ci ON c.codigo = ci.codigo WHERE ci.codigo_persona = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigoPaciente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Consulta> listarPorPacienteYMedico(String codigoPaciente, String codigoMedico) {
        ArrayList<Consulta> lista = new ArrayList<>();
        String sql = "SELECT c.codigo, c.precio, c.visibilidad, c.codigo_diagnostico, " +
                "ci.codigo_persona, ci.codigo_medico, ci.fecha, ci.estado " +
                "FROM CONSULTA c JOIN CITA ci ON c.codigo = ci.codigo " +
                "WHERE ci.codigo_persona = ? AND ci.codigo_medico = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigoPaciente);
            stmt.setString(2, codigoMedico);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Paciente> listarPacientesPorMedico(String codigoMedico) {
        ArrayList<Paciente> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT ci.codigo_persona FROM CONSULTA c " +
                "JOIN CITA ci ON c.codigo = ci.codigo WHERE ci.codigo_medico = ?";
        try (Connection con = ConnectionDB.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigoMedico);
            ResultSet rs = stmt.executeQuery();
            PersonaDAO personaDAO = new PersonaDAO();
            while (rs.next()) {
                lista.add((Paciente) personaDAO.buscarPersonaCompleta(rs.getString("codigo_persona")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}