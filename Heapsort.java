import java.io.*;

class Series_Filmes {
    private String nome;
    private String idioma;
    private String formato;
    private String duracao;
    private String pais;
    private String emissora;
    private String transmissao;
    private int temporadas;
    private int episodios;

    // Construtor vazio
    public Series_Filmes() {
        this.idioma = "";
        this.nome = "";
        this.duracao = "";
        this.formato = "";
        this.pais = "";
        this.emissora = "";
        this.transmissao = "";
        this.temporadas = 0;
        this.episodios = 0;
    }

    // Construtor que recebe parâmetros
    Series_Filmes(String nome, String idioma, String duracao, String formato, String pais, String emissora, String transmissao,
            int temporadas, int episodios) {
        this.nome = nome;
        this.idioma = idioma;
        this.duracao = duracao;
        this.formato = formato;
        this.pais = pais;
        this.emissora = emissora;
        this.transmissao = transmissao;
        this.temporadas = temporadas;
        this.episodios = episodios;
    }

    // encapsulamento das variáveis
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getFormato() {
        return this.formato;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDuracao() {
        return this.duracao;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return this.pais;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public String getEmissora() {
        return this.emissora;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public String getTransmissao() {
        return this.transmissao;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getTemporadas() {
        return this.temporadas;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }

    public int getEpisodios() {
        return this.episodios;
    }

    public void clone(Series_Filmes x) {
        this.nome = x.getNome();
        this.idioma = x.getIdioma();
        this.duracao = x.getDuracao();
        this.formato = x.getFormato();
        this.pais = x.getPais();
        this.emissora = x.getEmissora();
        this.transmissao = x.getTransmissao();
        this.temporadas = x.getTemporadas();
        this.episodios = x.getEpisodios();
    }

    public static String removetags(String s) {
        String resp = "";
        boolean dentroTag = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '<') {
                // resp += s.charAt(i);
                dentroTag = false;
            } else if (s.charAt(i) == '>') {
                // resp += s.charAt(i);
                dentroTag = true;
            } else if (dentroTag == true) {
                resp += s.charAt(i);
            }
        }
        return resp;
    }

    public static int trataInt(String s) {
        int resp = 0;
        String aux = "";
        // MyIO.print(s);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3'
                    || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7'
                    || s.charAt(i) == '8' || s.charAt(i) == '9') {
                aux += s.charAt(i);
            }
            if (s.charAt(i) == ' ') {
                break;
            }
        }
        resp = Integer.parseInt(aux);

        return resp;
    }

    public static String consertaPais(String s) {
        String resp = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '&') {
                i += 6;
            }
            resp += s.charAt(i);
        }

        return resp;
    }

    public static String consertatransmissao(String s) {
        String resp = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '&') {
                i += 6;
            }
            resp += s.charAt(i);
        }

        return resp;
    }


    
    public String concertaNome(String fileName){
        String resp = "";
        for(int i = 0; i < fileName.length(); i++){
            if(fileName.charAt(i)  == '_'){ 
                resp += ' ';
            } else { 
                resp += fileName.charAt(i);
            }
        }
        return resp.substring(0, resp.length()-5); 
    }

    
    public void ler(String nome, String endereco) throws Exception {
        InputStreamReader irs = new InputStreamReader(new FileInputStream(endereco));

        BufferedReader br = new BufferedReader(irs);

        // leitura do nome
        while (!br.readLine().contains("<table class=\"infobox_v2\""))
            ;
        br.readLine();

        this.nome = concertaNome(nome);
        // leitura do formato

        while (!br.readLine().contains(">Formato<"))
            ;
        setFormato(removetags(br.readLine()));
        // leitura da duração
        while (!br.readLine().contains(">Dura"))
            ;
        setDuracao(removetags(br.readLine()).trim());
       // leitura do pais de origem
        while (!br.readLine().contains("s de origem<"))
            ;
        setPais(consertaPais(removetags(br.readLine())).trim());
        // leitura do idioma
        while (!br.readLine().contains(">Idioma"))
            ;
        setIdioma(removetags(br.readLine()).trim());
        // leitura da emissora de tv
        while (!br.readLine().contains(">Emissora de televis"))
            ;
        setEmissora(removetags(br.readLine()).trim());
       // leitura da transmissao
        while (!br.readLine().contains(">Transmiss"))
            ;
        setTransmissao(consertatransmissao(removetags(br.readLine())).trim());
        // leitura do n de temporadas
        while (!br.readLine().contains(" de temporadas<"))
            ;
        setTemporadas(trataInt(removetags(br.readLine())));
        // leitura do n de episodios
        while (!br.readLine().contains(" de epis"))
            ;
        setEpisodios(trataInt(removetags(br.readLine())));

        br.close();
    }

   
    public String imprimir() {
        return this.nome + " " + this.formato + " " + this.duracao + " " + this.pais + " " + this.idioma + " " + this.emissora + " " + this.transmissao + " " + this.temporadas + " " + this.episodios;

    }

}

public class Filmes {
    private Series_Filmes series[];
    private int n;
    public Filmes (int maxTam) { 
        this.series = new Series_Filmes[maxTam];
        this.n = 0;
    }


    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
 

    //insere no inicio
    void inserirInicio(Series_Filmes x) throws Exception {
        if (n >= series.length)
        throw new Exception("Erro!");
        for (int i = n; i > 0; i--){
        series[i] = series[i-1];
        }
        series[0] = x;
        n++;
        }

    void inserirFim(Series_Filmes x) throws Exception {
        if (n >= series.length)
        throw new Exception("Erro!");
        series[n] = x;
        n++;
    }
    
    void inserir(Series_Filmes x, int pos) throws Exception {
        if (n >= series.length || pos < 0 || pos > n)
        throw new Exception("Erro!");
        for (int i = n; i > pos; i--){
        series[i] = series[i-1];
        }
        series[pos] = x;
        n++;
        }

    Series_Filmes removerInicio() throws Exception {
        if (n == 0)
        throw new Exception("Erro!");
        Series_Filmes resp = series[0];
        n--;
        for (int i = 0; i < n; i++){
        series[i] = series[i+1];
        }
        return resp;
        }

    Series_Filmes removerFim() throws Exception {
        if (n == 0)
        throw new Exception("Erro!");
        return series[--n];
    }

    Series_Filmes remover(int pos) throws Exception {
        if (n == 0 || pos < 0 || pos >= n)
        throw new Exception("Erro!");
        Series_Filmes resp = series[pos];
        n--;
        for (int i = pos; i < n; i++){
        series[i] = series[i+1];
        }
        return resp;
        }
        
        void swap(int x, int y){
            Series_Filmes tmp = series[x];
            series[x] = series[y];
            series[y] = tmp;
        }

    void mostrar (){
        
        for (int i = 0; i < n; i++){
            MyIO.println(series[i].imprimir());
        }
        
    } 

   // metodos de ordenação
    
    void selecao(){
        for (int i = 0; i < (n - 1); i++) {
            int menor = i;
            for (int j = (i + 1); j < n; j++){
               if (series[menor].getPais().compareTo(series[j].getPais()) > 0 ){
                  menor = j;
               }else if(series[menor].getPais().equals(series[j].getPais())){
                   if(series[menor].getNome().compareTo(series[j].getNome()) > 0 ){
                       menor = j;
                   }
               }
            }
            swap(menor, i);
         }
    }

    void insercao() {
		for (int i = 1; i < n; i++) {
			Series_Filmes tmp = series[i];
         int j = i - 1;

         while ((j >= 0) && ((series[j].getIdioma().compareTo(tmp.getIdioma()) > 0) ||( (series[j].getIdioma().compareTo(tmp.getIdioma()) == 0) && (series[j].getNome().compareTo(tmp.getNome()) > 0)))){
            series[j + 1] = series[j];
            j--;                
         }
         series[j + 1] = tmp;
	}
}
   
    public void Heapsortt() {
        //Alterar o vetor ignorando a posicao zero
        Series_Filmes[] tmp = new Series_Filmes[n+1];
        for(int i = 0; i < n; i++){
           tmp[i+1] = series[i];
        }
        series = tmp;
  
        //Contrucao do heap
        for(int tamHeap = 2; tamHeap <= n; tamHeap++){
           construir(tamHeap);
        }
  
        //Ordenacao propriamente dita
        int tamHeap = n;
        while(tamHeap > 1){
           swap(1, tamHeap--);
           reconstruir(tamHeap);
        }
  
        //Alterar o vetor para voltar a posicao zero
        tmp = series;
        series = new Series_Filmes[n];
        for(int i = 0; i < n; i++){
           series[i] = tmp[i+1];
        }
     }
  
  
     public void construir(int tamHeap){
        
        for(int i=tamHeap;((i>1)&&((series[i].getFormato().compareTo(series[i/2].getFormato())> 0)||((series[i].getFormato().compareTo(series[i/2].getFormato()) == 0)&&(series[i].getNome().compareTo(series[i/2].getNome()) > 0)))); i/=2){
                swap(i, i/2);
        }
     }
     

     public void reconstruir(int tamHeap){
        int i = 1;
        while(i <= (tamHeap/2)){
           int filho = getMaiorFilho(i, tamHeap);
           if(series[i].getFormato().compareTo(series[filho].getFormato())< 0 || (series[i].getFormato().compareTo(series[filho].getFormato()) == 0  && series[i].getNome().compareTo(series[filho].getNome()) < 0 )) {
            // || series[i].getFormato().compareTo(series[filho].getFormato()) == 0  && series[i].getNome().compareTo(series[filho].getNome()) < 0
              swap(i, filho);

              i = filho;
           }else{
              i = tamHeap;
           }
        }
     }
  
     public int getMaiorFilho(int i, int tamHeap){
        int filho;
        if (2*i == tamHeap || series[2*i].getFormato().compareTo(series[2*i+1].getFormato()) > 0 || (series[2*i].getFormato().compareTo(series[2*i+1].getFormato()) == 0  && series[2*i].getNome().compareTo(series[2*i+1].getNome()) > 0 )){
            // || series[2*i].getFormato().compareTo(series[2*i+1].getFormato()) == 0  && series[2*i].getNome().compareTo(series[2*i+1].getNome()) > 0
           filho = 2*i;
        } else {
           filho = 2*i + 1;
        }
        return filho;
     }
  

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        String[] entrada = new String[1000];
        int numEntrada = 0;
        Filmes list = new Filmes(70);
        Series_Filmes s;
        // String url = "";
        String url = "/tmp/series/";

        // Leitura do html
        do {
            entrada[numEntrada] = MyIO.readLine();
            String endereco = url + entrada[numEntrada];
            s = new Series_Filmes();
            if (isFim(entrada[numEntrada]) == false) {
                s.ler(entrada[numEntrada], endereco);
                list.inserirFim(s);
            }
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;

    // execução da ordenação na list e mostrando depois
        list.Heapsortt();
        list.mostrar();

    }
}