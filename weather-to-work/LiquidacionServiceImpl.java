
public class LiquidacionServiceImpl implements LiquidacionService {
	public LiquidacionServiceImpl(
			this.liquidacionRepository = liquidacionRepository;
}

@Override
public LiquidacionDTO generarLiquidacion(long idTarjeta, int anio, int mes) throws TarjetaInexistenteException {
	Optional<Tarjeta> opTarjeta = tarjetaRepository.buscarPorId(idTarjeta);
	if(opTarjeta.isEmpty()) throw new TarjetaInexistenteException(idTarjeta);
	Tarjeta tarjeta = opTarjeta.get();

	ItemLiquidacion total = new ItemLiquidacion();
	List<Consumo> consumos = consumoRepository.buscarPorTarjetaYPeriodo(tarjeta.getNumero(), anio, mes);
	for (Consumo c : consumos) {
		total = total.acumular(this.calcularConsumo(c));
	}
	// consumo.stream.map( ... );

	Liquidacion l = new Liquidacion(tarjeta, anio, mes);
	l.setTotalConsumos(total.getConsumos());
	l.setTotalImpuestos(total.getImpuestos());
	l.setTotalDescuentos(total.getDescuentos());
	l.setTotalAPagar(total.getTotal());
	liquidacionRepository.guardar(l);

	return map(l);
}

private LiquidacionDTO map(Liquidacion l) {
	LiquidacionDTO dto = new LiquidacionDTO();
	dto.setId(l.getId());
	dto.setAnio(l.get());
	dto.setMes(l.get());
	dto.setNumeroTarjeta(l.get());
	dto.setTitular(l.get());
	dto.setTotalAPagar(l.get());
	dto.setTotalConsumos(l.get());
	dto.set(l.get());
	dto.set(l.get());
}

private ItemLiquidacion calcularConsumo(Consumo c) {
	doble consumo = c.getMonto();
	doble impuesto = 0;
	doble descuento = 0;

	if (c.getMoneda().equals("ARS)) {
		switch (c.getRubro()) {
			case COMBUSTIBLE: {
				descuento = Math.min(consumo * 0.15, 750);
				break;
			}
			case SUPERMERCADO: {
				descuento = Math.min(consumo * 0.20, 3000);
				break;
			}
			case RESTAURANTES: {
				if (c.getDia() >= 10 && c.getDia() <= 15) {
					descuento = Math.min(consumo * 0.15, 750);
				}
				break;
			}
			case OTROS: {
				impuesto += consumo * 0.12;
				break;
			}
		}
	} else {
		consumo *= this.cotizaciones.get(c.getMoneda());
		impuesto = consumo * 0.075;
	}

	return new ItemLiquidacion(consumo, impuesto, descuento);
}

@Override
public List<String> getLiquidacionesPendientes(int anio, int mes) {
	.map(t -> t.getNumero())
	.collect(Collectors.toList());
}

liquidarLote {
	return Files.lines(Paths.get(getClass().getClassLoader().getResource("liquidaciones.csv"))).get
		.map(l -> l.split(";"))
		.map(p -> this.generarLiquidacion(
					Long.parseLong(p[0]),
					Long.
					))
}
