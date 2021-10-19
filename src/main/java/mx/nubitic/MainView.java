package mx.nubitic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datepicker.DatePicker.DatePickerI18n;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.ui.Transport;

@Route
@Push(transport = Transport.LONG_POLLING)
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private VerticalLayout layoutEspacios = new VerticalLayout();
	private VerticalLayout layoutV2Combos = new VerticalLayout();
	private VerticalLayout layoutVCombos = new VerticalLayout();
	private VerticalLayout layoutV3Combos = new VerticalLayout();
	private VerticalLayout layoutVGrid = new VerticalLayout();
	private VerticalLayout mainLayout = new VerticalLayout();
	private HorizontalLayout layoutHCombos = new HorizontalLayout();
	private HorizontalLayout layoutHFechas = new HorizontalLayout();
	private HorizontalLayout scrnControlPaginador = new HorizontalLayout();
	private HorizontalLayout scrnIrPagina = new HorizontalLayout();

	private Button backButton = new Button("Ant.", new Icon(VaadinIcon.ARROW_LEFT));
	private Button btnBuscar = new Button("Buscar");
	private Button nextButton = new Button("Sig.", new Icon(VaadinIcon.ARROW_RIGHT));
	private ComboBox<Integer> irPaginaCB = new ComboBox<>();
	private ComboBox<String> operacionMovimientoCB = new ComboBox<>("Operación");
	private ComboBox<String> ramoCB = new ComboBox<>("Ramo");
	private ComboBox<String> estatusCB = new ComboBox<>("Estatus");
	private ComboBox<String> urCB = new ComboBox<>("Unidad Responsable");
	private DatePicker datePickerFechaFinal = new DatePicker("Fecha Registro Final");
	private DatePicker datePickerFechaInicio = new DatePicker("Fecha Registro Inicial");

	private Label lblIrPagina = new Label();
	private Label lblPagInfo = new Label();
	private Label lblRegistrosxdey = new Label();
	private Label lblResultadoBusqueda = new Label("Resultados de la Búsqueda");
	private List<String> listRamoUsr = new ArrayList<>();
	private List<String> listaEstatus = new ArrayList<>();
	private List<String> listURUsr = new ArrayList<>();
	private List<String> listOperacionMovimiento = new ArrayList<>();

	private TextField folioField = new TextField("Folio");

	public MainView() {
		listRamoUsr = Arrays.asList("11 - Ramo 11", "25 - Ramo 25", "33 - Ramo 33");

		mainLayout.setWidth("100%");
		folioField.setWidth("80%");
		folioField.setPlaceholder("Folio");
		ramoCB.setLabel("Ramo");
		ramoCB.setWidth("80%");
		ramoCB.setRequired(true);
		ramoCB.setPlaceholder("Seleccionar Ramo");
		ramoCB.setItems(listRamoUsr);
		ramoCB.addValueChangeListener(evnt -> {
			String ramo = evnt.getValue();
			int indice = 0;
			
			if (ramo != null) {
				listURUsr = Arrays.asList("ABC - UR ABC","DEF - UR DEF","GHI - UR GHI");
			} else {
				listURUsr.clear();
			}
			urCB.setItems(listURUsr);
		});

		ramoCB.addValueChangeListener(evnt2 -> {
			String ramo = ramoCB.getValue();
			
			if (ramo != null) {
				listOperacionMovimiento = Arrays.asList("Op 1","Op 2","Op 3");
				operacionMovimientoCB.setItems(listOperacionMovimiento);
			}
			
		});

		urCB.setWidth("80%");
		urCB.setPlaceholder("Seleccionar Unidad Responsable");
		urCB.setRequired(true);
		operacionMovimientoCB.setWidth("80%");
		operacionMovimientoCB.setPlaceholder("Seleccionar Operación");
		listaEstatus.add("Autorizado");
		listaEstatus.add("Rechazado");
		listaEstatus.add("En Proceso");
		estatusCB.setWidth("80%");
		estatusCB.setItems(listaEstatus);
		estatusCB.setRequired(true);
		estatusCB.setPlaceholder("Seleccionar Estatus");
		layoutVCombos.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		layoutVCombos.setAlignItems(Alignment.CENTER);
		layoutVCombos.add(folioField);
		layoutVCombos.add(ramoCB);
		layoutVCombos.add(urCB);
		layoutV2Combos.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		layoutV2Combos.setAlignItems(Alignment.CENTER);
		layoutHCombos.setVerticalComponentAlignment(Alignment.BASELINE, layoutVCombos);
		layoutHCombos.setVerticalComponentAlignment(Alignment.BASELINE, layoutV2Combos);
		layoutV2Combos.add(operacionMovimientoCB);
		layoutV2Combos.add(estatusCB);
		datePickerFechaInicio.setPlaceholder("Fecha Registro Inicial");
		datePickerFechaInicio.setWidthFull();
		datePickerFechaFinal.setPlaceholder("Fecha Registro Final");
		datePickerFechaFinal.setWidthFull();
		layoutHFechas.setWidth("80%");
		datePickerFechaInicio.setI18n(getDatePickerEspaniol());
		layoutHFechas.add(datePickerFechaInicio);
		datePickerFechaFinal.setI18n(getDatePickerEspaniol());
		layoutHFechas.add(datePickerFechaFinal);
		layoutV2Combos.add(layoutHFechas);
		layoutHCombos.setWidth("100%");
		layoutHCombos.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		layoutHCombos.setAlignItems(Alignment.CENTER);
		layoutHCombos.add(layoutVCombos);
		layoutHCombos.add(layoutV2Combos);
		layoutV3Combos.setAlignItems(Alignment.CENTER);
		layoutV3Combos.add(layoutHCombos);
		layoutV3Combos.add(btnBuscar);
		layoutV3Combos.getStyle().set("border", "1px solid #9E9E9E");
		mainLayout.add(layoutV3Combos);
		layoutEspacios.add(new Label(" "));
		layoutEspacios.add(new Label(" "));
		layoutEspacios.add(new Label(" "));
		layoutEspacios.add(new Label(" "));
		layoutEspacios.add(new Label(" "));
		layoutEspacios.add(new Label(" "));
		layoutEspacios.add(new Label(" "));
		mainLayout.add(layoutEspacios);

		scrnIrPagina.setWidth("100%");
		irPaginaCB.setWidth("auto");
		irPaginaCB.setClassName("my-combobox");
		lblIrPagina.add("Ir a Página: ");
		scrnIrPagina.add(lblIrPagina, irPaginaCB);
		scrnIrPagina.setJustifyContentMode(JustifyContentMode.CENTER);
		scrnControlPaginador.setWidth("100%");
		lblRegistrosxdey.add("Mostrando registros del: ");
		scrnControlPaginador.add(backButton, lblPagInfo, nextButton, lblIrPagina, irPaginaCB, lblRegistrosxdey);
		scrnControlPaginador.setJustifyContentMode(JustifyContentMode.CENTER);
		scrnControlPaginador.setVisible(false);
		layoutVGrid.getStyle().set("border", "1px solid #9E9E9E");
		layoutVGrid.add(lblResultadoBusqueda);

		layoutVGrid.setVisible(false);
		mainLayout.add(layoutVGrid);
		mainLayout.add(scrnIrPagina);
		mainLayout.add(scrnControlPaginador);
		btnBuscar.addClickListener(event -> {
			layoutVGrid.setVisible(false);
			nextButton.setEnabled(false);
			backButton.setEnabled(false);
			irPaginaCB.setEnabled(false);
			scrnControlPaginador.setVisible(false);

			if (!layoutEspacios.isVisible() && !layoutVGrid.isVisible()) {
				layoutEspacios.setVisible(true);
			}
		});



		add(mainLayout);
	}

	private DatePickerI18n getDatePickerEspaniol(){
		DatePickerI18n dp = new DatePicker.DatePickerI18n().setWeek("Semana")
	    .setCalendar("Calendario").setClear("Limpiar")
	    .setToday("Hoy").setCancel("Cancelar").setFirstDayOfWeek(1)
	    .setMonthNames(Arrays.asList("Enero", "Febrero", "Marzo",
	            "Abril", "Mayo", "Junio", "Julio", "Agosto",
	            "Septiembre", "Octubre", "Noviembre", "Diciembre"))
	    .setWeekdays(Arrays.asList("Domingo", "Lunes", "Martes",
	            "Miércoles", "Jueves", "Viernes", "Sábado"))
	    .setWeekdaysShort(Arrays.asList("Do", "Lu", "Ma", "Mi", "Ju",
	            "Vi", "Sá"));
		return dp;
	}
	
}