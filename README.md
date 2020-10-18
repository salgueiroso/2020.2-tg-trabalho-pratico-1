# 2020.2-tg-trabalho-pratico-1

# Definição

### Modelo
O trabalho utilizará lista de adjascências para representar os grafos de entrada em arquivos no seguinte formato:

![Grafo de exemplo](https://graphonline.ru/tmp/saved/fl/flByUWJgTXMJmnxB.png)

O grafo direcionado acima pode ser representado da seguinte forma:

```
A:B,D,E
B:A,C
C:D,E
D:
E:D,A,B
```

> O arquivo [Grafo1.txt](https://github.com/salgueiroso/2020.2-tg-trabalho-pratico-1/blob/master/Grafo1.txt) poderá ser utilizado como exemplo de entrada para o algoritmo.

### Projeto Java
O projeto é composto de 2 sub projetos, um com a implementação solicitada na atividade e um projeto de teste (JUnit).

### Estrutura Básica
- `IGrafo` Interface com os metodos solicitados na atividade.
- `Grafo` Classe que implementa todos os metodos relacionados aos grafos.
- `GrafoString` Implementa a classe grafo de forma que trate cada vértice como uma string.
- `Vertice` Modelo base de cada vertice. Esta classe armazena flags basicos relacionada a cada vertice.

### Execução
Basicamente a execução se dá instanciando a classe `GrafoString` e chamando seu método `carregarGrafoDoTexto` informando como parâmetro a string no padrão de lista encadeada como definodo no [modelo acima](#modelo) 


# Descrição do Trabalho
Esta atividade consiste na implementação de alguns métodos de Grafos. O objetivo é fazer com que os alunos, em grupos, pesquisem, discutam e decidam formas de implementar os conceitos de grafos visto em sala de aula.

Cada grupo deve implementar os seguintes requisitos:

### Representação do Grafo
Cada grupo deve escolher como representar o grafo. Na aula, vimos algumas estruturas que permitem isso: matriz de adjacência, matriz de incidência e lista de adjacência. No entanto, cada grupo está livre para pesquisar e encontrar outras maneiras de representação.

### Métodos Básicos
Os seguintes métodos básicos devem ser implementados:

- **getAdjacentes:** retorna a lista de adjacentes de um vértice passado como parâmetro.
- **ehRegular:** verifica se um determinado grafo é regular ou não. Deve retornar True ou False a depender do grafo.
- **ehCompleto:** verifica se um determinado grafo é completo ou não. Deve retornar True ou False a depender do grafo.
- **ehConexo:** verifica se um determinado grafo é conexo ou não. Deve retornar True ou False a depender do grafo. Deve utilizar busca em largura ou busca em profundidade para fazer essa verificação.

### Algoritmos
- **Algoritmo de Menor Caminho (Dijkstra):** deve implementar o algoritmo de Dijkstra que calcula o menor caminho de um vértice a outro no grafo. Devem ser implementados duas versões desse algoritmo:
  - A primeira recebe como parâmetro um vértice e o algoritmo retorna o menor caminho deste para todos os demais vértices. Neste caso, deve-se imprimir na tela a menor distância para cada par de vértice e o caminho final entre eles.
  - A segunda recebe como parâmetro dois vértices e o algoritmo retorna o menor caminho somente entre estes dois vértices. Neste caso, deve-se imprimir na tela a menor distância entre eles e o caminho final.
> **Atenção**
> 
> A atividade pode ser feita em grupos de até 3 pessoas
> Deve ser implementado, preferencialmente, nas linguagens: Python, Java ou C++. A implementação em outra linguagem deve ser discutida com o professor.
> O código produzido deve ser versionado em um repositório do GitHub.
> Junto com o código deve ter um arquivo README.md com as instruções de como o projeto deve ser testado.

### Avaliação
Serão avaliados os seguintes critérios:

- **Implementação dos métodos solicitados:** se o grupo implementou todas os métodos pedidos.
- **Representação do grafo:** se o grupo representou o grafo de forma adequada e que permita executar os algoritmos solicitados.
- **Corretude da implementação:** se o grupo implementou de forma correta os métodos solicitados de acordo com o que foi pedido na descrição de cada um deles.
- **Participação e comprometimento** dos componentes do grupo com o trabalho.
- **Entrega** nos prazos determinados.
> **A pontuação está descrita no memorial de avaliação**

### Entrega
As entregas serão feitas ao longo da unidade da seguinte forma:

- **Primeira entrega:** criar o repositório e fazer o commit do código referente a representação do grafo em computador.
- **Segunda entrega:** fazer o commit dos métodos básicos.
- **Terceira entrega:** fazer o commit dos algoritmos solicitados e preencher o formulário de entrega associadoà tarefa correspondente.
> As datas de cada entrega estão no cronograma da disciplina. Não serão aceitas entregas fora do prazo.

> **Importante**
> 
> Não serão aceitos projetos que não foram desenvolvidos pelo grupo. Qualquer tipo de cópia será desconsiderada e os alunos envolvidos na cópia receberão nota 0 para toda atividade.
