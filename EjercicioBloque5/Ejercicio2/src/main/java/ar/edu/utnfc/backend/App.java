package ar.edu.utnfc.backend;

import java.util.List;

import ar.edu.utnfc.backend.model.Empleado;
import ar.edu.utnfc.backend.service.CsvLoader;
import ar.edu.utnfc.backend.service.InformesService;
import ar.edu.utnfc.backend.data.CategoriaFactory;
import ar.edu.utnfc.backend.service.InformesService;

/**
 * Hello world!
 */
public class App {

        public static void main(String[] args) {

                var categorias = CategoriaFactory
                                .crearCategorias();

                CsvLoader loader = new CsvLoader();

                List<Empleado> empleados = loader.cargar(
                                "data/empleados.csv",
                                categorias);

                System.out.println(
                                "Cantidad cargada: "
                                                + empleados.size());

                var informes = new InformesService(empleados);

                informes.informarMayorYMenor();

                informes.totalSueldosPorTipo();

                informes.porcentajeContratados();

                informes.antiguedadPromedio();
        }
}
