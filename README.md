# Challenge Backend BCredi

Desafio de um processo seletivo de 2019 da BCredi, realizado para praticar Java.

Repositório do desafio: https://github.com/bcredi/teste-backends

Desafio encontrado em: https://github.com/CollabCodeTech/backend-challenges

### Resumo

Neste desafio o objetivo era manipular um conjunto de eventos (escritos em arquivos de texto) que representavam ações de um sistema de propostas de empréstimos. As propostas válidas gerados por eventos válidos deveriam ser retornadas.

Uma proposta de empréstimo é composta valor, parcelas, proponentes (entidades envolvidas) e garantias.

No desafio existem 8 regras de validação para uma proposta ser válida, e 2 regras para um evento ser válido, que podem ser encontradas no repositório original.

### Solução

A solução proposta consiste em um gerenciador de eventos (EventManager) e um serviço para gerenciar as propostas (ProposalService).

O EventManager é composto por dois métodos públicos: um para adicionar (add) um evento na sua coleção de eventos a partir de um vetor de strings construído a partir de uma linha do arquivo de entrada, que representa um evento; outro para processar todos os eventos (processAll). A implementação da coleção de eventos são dois mapas ordenados (LinkedHashMap) abstraídos pela class EventMap, para garantir a consistência entre ambos mapas. A escolha de dois mapas foi para atender duas as duas restrições de adição de eventos e garantir um processamento otimizado (rápido).

Cada evento das coleções do EventMap é representando por uma classe abstrata Event que tem um método abstrato *process* para processar o evento.

Como os dados não são persistidos, e por simplicidade, não foi criado um módulo de repositórios, portanto na classe de serviços está presente coleção de propostas (Proposal) e ela é responsável por manipular tal coleção: adicionando, atualizando, removendo e validando. Essa classe é invocada por cada evento e também possui um método público para retornar a lista de propostas válidas (getValidProposals).

Para a validação existe uma classe no pacote de serviço (ProposalValidation) que guarda uma coleção de regras de validações, e um método para aplicar todas as validações em uma proposta. Uma regra de validação implementa a classe ValidationRule que possui apenas um método de validação: *isValid* .

A solução pode ser executada pelo jar solution.jar:

`java -jar solution.jar`

Também está disponível no meu dockerhub: andreepdias/challenge-backend-bcredi

`docker pull andreepdias/challenge-backend-bcredi`

`docker run andreepdias/challenge-backend-bcredi`

Arquivo de saída:

```
Starting tests...
Test 1/13 - Passed
Test 2/13 - Passed
Test 3/13 - Passed
Test 4/13 - Passed
Test 5/13 - Passed
Test 6/13 - Passed
Test 7/13 - Passed
Test 8/13 - Passed
Test 9/13 - Passed
Test 10/13 - Passed
Test 11/13 - Passed
Test 12/13 - Passed
Test 13/13 - Passed
```
