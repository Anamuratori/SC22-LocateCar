package org.locadora.views;


import org.locadora.controller.AgencyController;
import org.locadora.model.Address;
import org.locadora.model.Agency;
import org.locadora.utils.Input;
import org.locadora.utils.InputAddress;
import org.locadora.utils.MenuCreator;
import org.locadora.utils.Pagination;


import java.util.List;

public class AgencyUI {
    public static void add() {
        AgencyController agencyController = new AgencyController();

        try {
            String name = Input.stringNotNullable("NOME DA AGÊNCIA: ", 3);
            Address address = InputAddress.exec("");
            agencyController.saveAgency(name, address);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }
    }

    public static String list(List<Agency> agencies, int pageSize, int pageNumber) {
        String option = "";

        boolean working = true;

        try {
            while (working) {
                List<Agency> paginatedAgencies = Pagination.exec(agencies, pageSize, pageNumber);


                System.out.println("------ OPERAÇÕES ------");
                System.out.println("");
                for (Agency agency : paginatedAgencies) {
                    System.out.println(agency.toString());
                }
                System.out.println("--------------------------");

                if (agencies.size() == 0) {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR OPERACAO");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 1:
                            add();
                            break;
                        default:
                            System.out.println("OPÇÃO INVÁLIDA\n");
                            break;
                    }
                } else if (agencies.size() > pageSize) {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "EXIBIR OPERACAO", "ADICIONAR OPERACAO");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 1:
                            list(agencies, pageSize, pageNumber + pageSize);
                            break;
                        case 2:
                            list(agencies, pageSize, pageNumber - pageSize);
                            break;
                        case 3:
                            working = false;
                            break;
                        case 4:
                            add();
                            break;
                        default:
                            System.out.println("OPÇÃO INVÁLIDA\n");
                            break;
                    }

                } else {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "EXIBIR OPERACAO", "ADICIONAR OPERACAO");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 2:
                            option = "EDITAR";
                            break;
                        case 3:
                            add();
                            break;
                        default:
                            System.out.println("OPÇÃO INVÁLIDA\n");
                            break;
                    }
                }
            }
        } catch (Exception ex) {
            working = false;
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }

        return option;
    }

    public static void viewAgency(Agency agency, int index) {
        AgencyController agencyController = new AgencyController();
        boolean working = true;
        try {
            while (working) {
                System.out.println("------- AGÊNCIA -------");
                System.out.println("");
                agency.toString();

                int option = MenuCreator.exec(".:: OPÇÔES DE CONTATO ::.", "VOLTAR", "CADASTRAR VEÍCULO", "LISTAR VEICULOS", "EDITAR NOME", "EDITAR ENDERECO");

                switch (option) {
                    case 0 -> {
                        working = false;
                    }
//                case 1 -> /*Cadastrar veículo*/;
//                case 2 -> /*Listar Veículos*/
                    case 3 -> agencyController.edit("name", agency, index);
                    case 4 -> agencyController.edit("address", agency, index);
                    default -> System.out.println("-> Opção inválida \n");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }
    }

    public static String searchBy() {
        String response = "";
        boolean working = true;
        try {
            while (working) {

                int option = MenuCreator.exec("COMO GOSTARIA DE REALIZAR A PESQUISA? ", "VOLTAR", "CODIGO DA AGENCIA", "NOME/LOGRADOURO");
                switch (option) {
                    case 0 -> working = false;
                    case 1 -> response = "codigo";
                    case 2 -> response = "name";
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }
        return response;
//        agencyController.search(term.toUpperCase());
    }
}
