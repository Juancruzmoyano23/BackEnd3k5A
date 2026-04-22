package ar.edu.utnfc.backend.EjercicioStream;

import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class InformeBeca {

    private String ciudad;
    private int cantidadElegibles;
    private double montoTotal;
    private String[] beneficiarios;

    public InformeBeca(){
        this.ciudad = "Cordoba";
        this.cantidadElegibles = 0;
        this.montoTotal = 0;
        this.beneficiarios = new String[]{""};
    }

    public String montoPorCategoria(int monto){
        if(monto == 3000){
            return "BUENO";
        } else if (monto == 50000){
            return  "EXELENTE";
        }
        return "";
    }

    public InformeBeca sistemaDeBecas(List<Estudiante> estudiantes){
        List<PerfilEstudiante> listaPerfiles = estudiantes.stream()
                .map(est -> {
                    PerfilEstudiante perfil = new PerfilEstudiante();
                    perfil.setEstudiante(est);
                    perfil.setCategoria(new CategoriaRendimiento());
                    perfil.setElegibleBeca(perfil.obtenerElegibleBeca(est.getNota(), est.getEdad()));
                    return perfil;
                })
                .toList();

            // particionar perfiles en elegibles o no elegibles
            Map<Boolean, List<PerfilEstudiante>> elegibles = listaPerfiles.stream()
                    .collect(Collectors.partitioningBy(PerfilEstudiante::isElegibleBeca));

            // Agrupe los elegibles por ciudad y genere una List<InformeBecas>
            List<InformeBeca> informes = elegibles.get(true).stream()
                    .collect(Collectors.groupingBy(p -> p.getEstudiante().getCiudad()))
                    .entrySet().stream()
                    .map(entry -> {
                        String ciudad = entry.getKey();
                        List<PerfilEstudiante> perfilesCiudad = entry.getValue();

                        // calcular monto total

                        double montoTotal = perfilesCiudad.stream()
                                .mapToDouble(p -> {
                                    String categoria = CategoriaRendimiento.desdeNota(p.getEstudiante().getNota());
                                    if (categoria.equals("EXELENTE")) {return 50000;}
                                    else if (categoria.equals("BUENO")) {return 3000;}
                                    return 0;
                                })
                                .sum();

                        // obtener los nombre de los beneficiearios
                        String[] beneficiarios = perfilesCiudad.stream()
                                .map(p -> p.getEstudiante().getNombre())
                                .toArray(String[]::new);


                        // crear el informe de la beca
                        InformeBeca info = new InformeBeca();
                        info.setCiudad(ciudad);
                        info.setCantidadElegibles(perfilesCiudad.size());
                        info.setMontoTotal(montoTotal);
                        info.setBeneficiarios(beneficiarios);
                        return info;
                    })
                    .toList();

            // Encuentre la ciudad con mayor monto total de becas
            InformeBeca ciudadMayorMonto = informes.stream()
                    .max(Comparator.comparingDouble(InformeBeca::getMontoTotal))
                    .orElse(null);

            // Encuentre el beneficiario más joven
            PerfilEstudiante masJoven = elegibles.get(true).stream()
                    .min(Comparator.comparingInt(p -> p.getEstudiante().getEdad()))
                    .orElse(null);

            // Imprima el informe
            System.out.println("=== INFORME DE BECAS ===");
            informes.forEach(inf -> {
                System.out.println("Ciudad: " + inf.getCiudad());
                System.out.println("Elegibles: " + inf.getCantidadElegibles());
                System.out.println("Monto Total: $" + inf.getMontoTotal());
                System.out.println("Beneficiarios: " + String.join(", ", inf.getBeneficiarios()));
                System.out.println("---");
            });

            System.out.println();

            if (ciudadMayorMonto != null) {
                System.out.println("Ciudad con mayor monto: " + ciudadMayorMonto.getCiudad() +
                                   " ($" + ciudadMayorMonto.getMontoTotal() + ")");
            } else {
                System.out.println("No hay ciudades con becas asignadas.");
            }

            if (masJoven != null) {
                System.out.println("Beneficiario más joven: " + masJoven.getEstudiante().getNombre() +
                                   " (" + masJoven.getEstudiante().getEdad() + " años)");
            } else {
                System.out.println("No hay beneficiarios elegibles.");
            }
            return null;
        }
    }
