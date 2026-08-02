package logico;

import java.sql.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Reporte {

	public Reporte(Clinica clinica) {
		// ya no se usa el ArrayList en memoria, se consulta directo
	}

	public DefaultPieDataset generarVacunasMasAplicadas() {
		DefaultPieDataset data = new DefaultPieDataset();
		String sql = "SELECT v.nombre, COUNT(*) as cantidad " +
				"FROM PACIENTE_VACUNA pv JOIN VACUNA v ON pv.codigo_vacuna = v.codigo " +
				"WHERE pv.aplicada = true " +
				"GROUP BY v.nombre ORDER BY cantidad DESC LIMIT 10";
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				data.setValue(rs.getString("nombre"), rs.getInt("cantidad"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public DefaultCategoryDataset generarFrecuenciaEnfermedades() {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		String sql = "SELECT e.nombre, COUNT(*) as cantidad " +
				"FROM PACIENTE_ENFERMEDAD pe JOIN ENFERMEDAD e ON pe.codigo_enfermedad = e.codigo " +
				"GROUP BY e.nombre ORDER BY cantidad DESC LIMIT 5";
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				data.addValue(rs.getInt("cantidad"), "Frecuencia", rs.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public DefaultCategoryDataset consultasPorEsp() {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		String sql = "SELECT m.especialidad, COUNT(*) as cantidad " +
				"FROM CONSULTA c JOIN CITA ci ON c.codigo = ci.codigo " +
				"JOIN MEDICO m ON ci.codigo_medico = m.codigo " +
				"GROUP BY m.especialidad ORDER BY cantidad DESC";
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				data.addValue(rs.getInt("cantidad"), "Consultas", rs.getString("especialidad"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public DefaultPieDataset citasByEstado() {
		DefaultPieDataset data = new DefaultPieDataset();
		String sql = "SELECT estado, COUNT(*) as cantidad FROM CITA GROUP BY estado";
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String label = rs.getBoolean("estado") ? "Citas Completadas" : "Citas Pendientes";
				data.setValue(label, rs.getInt("cantidad"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public DefaultCategoryDataset top5MedicoMasConsulta() {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		String sql = "SELECT per.nombres, per.apellidos, COUNT(*) as cantidad " +
				"FROM CONSULTA c JOIN CITA ci ON c.codigo = ci.codigo " +
				"JOIN PERSONA per ON ci.codigo_medico = per.codigo " +
				"GROUP BY per.nombres, per.apellidos ORDER BY cantidad DESC LIMIT 5";
		try (Connection con = dao.ConnectionDB.obtenerConexion();
			 PreparedStatement stmt = con.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String nombreCompleto = rs.getString("nombres") + " " + rs.getString("apellidos");
				data.addValue(rs.getInt("cantidad"), "Consultas", nombreCompleto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
}