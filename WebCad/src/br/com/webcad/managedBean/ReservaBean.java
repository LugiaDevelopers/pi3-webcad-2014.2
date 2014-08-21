package br.com.webcad.managedBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.webcad.negocio.Fachada;
import br.com.webcad.negocio.IFachada;
import br.com.webcad.negocio.reserva.Reserva;

@ManagedBean(name = "reservaBM")
@SessionScoped
public class ReservaBean {

	private Reserva reserva;
	private IFachada fachada;
	private ArrayList<Reserva> reservas;
	private int chave = 0;

	private Date data1;
	// = new Date();
	// private SimpleDateFormat formataData = new
	// SimpleDateFormat("dd/MM/yyyy");
	// private String data = formataData.format(data1);
	//
	private String emailN ;

	public ReservaBean() {
		reserva = new Reserva();
		fachada = Fachada.getInstancia();
		emailN = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("email");
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
						format.format(event.getObject())));
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

	public Date getData1() {
		return data1;
	}

	public void setData1(Date data1) {
		this.data1 = data1;
	}

	public String salvar() {
		if (reserva.getId() > 0) {
			// desistirReserva();
		} else {
			inserirReserva();
		}

		return "realizar_reserva";
	}

	private void inserirReserva() {

		fachada.cadastrar(reserva);

		reservas = null;
		reserva = new Reserva();

	}

	public String buscarEquipamentosDisponiveis(String nome, String curso,
			String data) {
		// reservas = null; //add agora
		
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
		
		this.reservas = fachada
				.buscarEquipamentosDisponiveis(nome, curso, sdf1.format(data1));

		System.out.println("Entrou no busca equipamento " + nome + "\n" + curso
				+ "\n" + sdf1.format(data1));

		verificaLista();

		chave = 1;
		return "realizar_reserva";
	}

	public String efetuarReserva(int idEquip, String nome, String data) {
		System.out.println(idEquip + " -" + emailN + "- " + nome + "- " + data);

		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
		
		fachada.efetuarReserva(idEquip, emailN, nome, sdf1.format(data1));
		reservas = null;

		buscarEquipamentosDisponiveis(null, null, null);
		return "realizar_reserva";
	}

	public String verificarReservas() {
		reservas = null;// add agora
		emailN = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("email");
		System.out.println("entrou" + emailN);

		reservas = fachada.verificarReservas(emailN);

		// for (int i = 0; i < reservas.size(); i++) {
		// System.out.println(reservas.get(i).getEquipamento().getTipoEquipamento().getNome());
		// }

		return "home_professor";

	}

	public String desistirDaReserva(int idReserva) {

		System.out.println(idReserva);
		fachada.desistirDaReserva(idReserva);
		verificarReservas();

		return null;
	}

	public String listaLimpa() {

		if (chave == 0) {
			System.out.println("Limpou 0");
			reservas = null;

			return "realizar_reserva";

		} else {
			System.out.println("Limpou 1");
			chave = 0;
			return "realizar_reserva";
		}

	}

	private void verificaLista() {
		for (int i = 0; i < reservas.size(); i++) {
			System.out.println("Verificando lista de reservas: "
					+ reservas.get(i).getEquipamento().getTipoEquipamento()
							.getNome());
		}
	}

}
