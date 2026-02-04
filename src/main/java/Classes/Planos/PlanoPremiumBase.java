package Classes.Planos;

import java.io.Serializable;

public class PlanoPremiumBase implements PlanoSubscricao, Serializable {

    @Override
    public int pontosPorMusica(int pontosAtuais, boolean musicaNova){
        return 10;
    }

    public boolean podeCriarPlaylists(){
        return true;
    }

    public boolean podeAcederAFavoritos(){
        return false;
    }

    public String getNomePlano(){
        return "Plano PremiumBase";
    }
}
