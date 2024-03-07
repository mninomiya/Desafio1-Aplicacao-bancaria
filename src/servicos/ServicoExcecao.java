package servicos;

public class ServicoExcecao extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ServicoExcecao(String msg) {
		super(msg);
	}
}
