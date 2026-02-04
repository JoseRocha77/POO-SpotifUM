package Classes.Planos;

import java.io.Serializable;

public class PlanoFree implements PlanoSubscricao, Serializable {

    @Override
    public int pontosPorMusica(int pontosAtuais, boolean musicaNova){
        return 5;
    }

    public boolean podeCriarPlaylists(){
        return false;
    }

    public boolean podeAcederAFavoritos(){
        return false;
    }

    public String getNomePlano(){
        return "Plano Free";
    }
}
