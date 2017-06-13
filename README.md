# INE5430-06208 (20171) - Inteligência Artificial (fuzzy-parking)

Proposta de trabalho prático a nível de graduação sobre sistemas fuzzy,
sendo disponibilizada a plataforma necessária para o desenvolvimento do
trabalho. O trabalho proposto consiste em desenvolver o controlador fuzzy
de um motorista para estacionar um caminhão de ré numa doca, utilizando os
conhecimentos sobre sistemas fuzzy adquiridos durante as aulas.

A simulação será executada num programa em java já pronto com dois caminhões
em pátios separados. Cada caminhão será controlado por uma porta diferente
(4321 e 4322) por grupos diferentes, criando uma competição entre grupos no
dia da apresentação. Quando o caminhão sair muito da tela ou ultrapassar a
posição 1.0 no eixo y, a simulação será encerrada e uma pontuação será
calculada para beneficiar quem parar o caminhão com o ângulo mais próximo
de 90 graus, mais próximo do centro da doca ((x,y) = (0.5 , 1.0)) e utilizando
menos passos.

As coordenadas x e y serão fornecidas entre 0 e 1.0, sendo x=0 a borda esquerda,
x=1.0 a borda direita, y=0 o topo da tela e y=1.0 o fundo. A doca
(alvo de estacionamento) está em (0.5,1.0). O controle do veículo será feito
enviando um valor no intervalo [-1,1] para virar o volante, em -1.0 o volante
é virado 30o para a esquerda e em 1.0 o volante é virado 30o para a direita
(A cada passo é possível virar o caminhão no máximo 30 graus para ambos os lados).

Para receber as coordenadas, enviar uma string "r\r\n" (caractere r  +
carriage return + newline) ou simplesmente println("r") no java. As coordenadas
retornarão numa string com os valores em double, na ordem x y e angulo, delimitadas
por tab (\t). Será fornecido um programa em java que implementa o pooling das coordenadas
e espera pela entrada do usuário para fornecer uma ação entre -1 e 1 para o volante do
carrinho. É possível testar com esse programa digitando a ação desejada [-1,1] e implementar
seu trabalho a partir dele.

O grupo deve determinar as entradas do controlador, os conjuntos fuzzy a serem empregados,
e as regras sobre as quais será feita a inferência fuzzy. É recomendada a utilização da
biblioteca JFuzzyLogic  http://jfuzzylogic.sourceforge.net , ou seu clone em C++/Qt
http://sourceforge.net/projects/jfuzzyqt/

# Apresentação

ALÉM DA APRESENTAÇÃO (COMPETIÇÃO ENTRE AS EQUIPES) CADA EQUIPE DEVE ENTREGAR UM RELATÓRIO
CONTENDO INTRODUÇÃO, DESENVOLVIMENTO E CONCLUSÃO ONDE DEVEM CONSTAR:

- Um breve tutorial teórico sobre o sistema fuzzy;
- As entradas utilizadas por seu motorista;
- Como são os conjuntos fuzzy utilizados;
- Quais as regras utilizadas;
- Métdodo de defuzzificação utilizado;
- Dificuldades encontradas e como elas foram superadas.

# Eclipse

Incorpore o Maven ao projeto no seu Eclipse.

# JFuzzyLogic

Entre no site http://jfuzzylogic.sourceforge.net/html/index.html
e baixe o Core do projeto. Abra o Core do projeto no seu Eclipse
e adicione ele como dependência para esse projeto aqui. Você
pode também adicionar o .jar do JFuzzyLogic no projeto.

