package ifpr.pgua.eic.colecaomusicas;

import ifpr.pgua.eic.colecaomusicas.controllers.BucarAgenda;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroAgenda;
import ifpr.pgua.eic.colecaomusicas.controllers.Principal;
import ifpr.pgua.eic.colecaomusicas.daos.AgendaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.FabricaConexoes;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCAgendaDAO;
import ifpr.pgua.eic.colecaomusicas.repositories.ReposiorioAgenda;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

public class App extends BaseAppNavigator {

        private AgendaDAO agendaDAO = new JDBCAgendaDAO(FabricaConexoes.getInstance());
        private ReposiorioAgenda repositorioAgenda = new ReposiorioAgenda(agendaDAO);

        public static void main(String[] args) {
                launch();
        }

        @Override
        public String getHome() {
                return "PRINCIPAL";
        }

        @Override
        public String getAppTitle() {
                return "Agenda";
        }

        @Override
        public void registrarTelas() {
                registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o -> new Principal()));
                registraTela("CADASTROAGENDA",
                                new ScreenRegistryFXML(App.class, "cadastro.fxml",
                                                o -> new CadastroAgenda(repositorioAgenda)));
                registraTela("BUSCARAGENDA",
                                new ScreenRegistryFXML(App.class, "busca.fxml", o -> new BucarAgenda(repositorioAgenda)));
        }

}
