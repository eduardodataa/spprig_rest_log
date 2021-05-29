# spring_rest_log

Projeto de estudos da API Spring Rest.

Chamadas Rest para importar no Postman: https://www.getpostman.com/collections/b96b890a2fcfc0e11275
Documentação dos serviços da API: https://documenter.getpostman.com/view/7532908/TzXzDcg8

Projeto de estudos da API Spring Rest.
1. Springframework (utilização dos verbos para as respectivas ações)
2. Lombok
3. Jackson Dataformat
4. Sprint Data JPA
5. MySQL
6. Flyway (migrations)
7. Modelmapper
8. ValidationGroups
9. ResponseEntityExceptionHandler (tratamento centralizado/customizado de exceções)
10. Java 11 (para utilizar Lâmbida)
11. Internacionalização com messages.properties

Configuração do projeto:
1. Criar um banco de dados MySQL com schema *algalog*
2. Banco de dados na porta 3307
3. Usuário root. Senha 123456.
4. Executar a classe AlgalogApiApplication como aplicação java.
5. Verificar se as tabelas foram criadas com êxito.
6. Abrir o Postman e importar collections especificada no item acima. Os serviço estão listados no link "Documentação dos serviços da API"
