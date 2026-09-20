# README do repositório e do projeto Android da pasta `Calculadora`

Este repositório contém um aplicativo Android simples de calculadora, localizado na pasta `Calculadora/`. O nome interno do app é `CalculadoraNoite`, conforme definido no projeto Android. O app foi construído em Java, usando `AppCompatActivity`, `ConstraintLayout`, `LinearLayout`, `ScrollView` e recursos de internacionalização por meio de `strings.xml`.

O objetivo do projeto é receber dois números digitados pelo usuário, executar uma das quatro operações básicas (`+`, `-`, `*`, `/`) e exibir o resultado na tela.

---

## 1. Estrutura geral do projeto

```text
DM_DS34A_2_sem_26/
├── Calculadora/
│   ├── app/
│   │   ├── src/main/java/com/example/calculadoranoite/MainActivity.java
│   │   ├── src/main/res/layout/activity_main.xml
│   │   ├── src/main/res/layout-land/activity_main.xml
│   │   ├── src/main/res/values/strings.xml
│   │   ├── src/main/res/values-en-rCA/strings.xml
│   │   ├── src/main/AndroidManifest.xml
│   │   └── build.gradle.kts
│   ├── build.gradle.kts
│   └── settings.gradle.kts
└── README.md
```

### Papel de cada parte

- `MainActivity.java`: controla a tela, captura eventos de clique e realiza os cálculos.
- `activity_main.xml`: layout padrão, usado normalmente em orientação vertical.
- `layout-land/activity_main.xml`: layout alternativo para orientação horizontal.
- `strings.xml`: textos exibidos na interface.
- `AndroidManifest.xml`: registra a activity principal e define configurações globais do app.
- `build.gradle.kts`: define SDK, versão e dependências.

---

## 2. Como o aplicativo funciona

O fluxo básico do app é este:

1. O Android inicia a `MainActivity`.
2. O método `onCreate()` monta a tela.
3. O método `configuracao()` liga cada variável Java ao componente visual correspondente.
4. O método `eventos()` registra o que deve acontecer ao clicar em cada botão.
5. O usuário digita dois números.
6. Ao clicar em uma operação, o app chama `executar(op)`.
7. O texto digitado é convertido para `Double`.
8. O cálculo é feito.
9. O resultado é mostrado no `TextView` `txvResultado`.

---

## 3. Activity principal

A aplicação possui apenas **uma activity**: `MainActivity`.

Ela herda de `AppCompatActivity`, ou seja, participa do ciclo de vida padrão do Android e usa compatibilidade com versões modernas da interface AndroidX.

### Campos da activity

Na classe existem referências para os componentes de tela:

- `EditText edtPrimeiroNumero`
- `EditText edtSegundoNumero` (variável Java ligada ao `id` XML `edtSegundonumero`)
- `Button btnSomar`
- `Button btnSubtrair`
- `Button btnMultiplicar`
- `Button btnDividir`
- `TextView txvResultado`

Essas variáveis representam o elo entre a interface XML e a lógica Java.

---

## 4. Explicação detalhada dos comandos do código

## `onCreate(Bundle savedInstanceState)`

Esse é o primeiro método importante executado quando a activity é criada.

### `super.onCreate(savedInstanceState);`
Chama a implementação da classe pai. Sem isso, a activity não é inicializada corretamente.

### `EdgeToEdge.enable(this);`
Permite que a interface ocupe a área total da tela, inclusive regiões próximas às barras do sistema.

### `setContentView(R.layout.activity_main);`
Define qual arquivo XML será usado para montar a interface visual da activity.

### `ViewCompat.setOnApplyWindowInsetsListener(...)`
Configura um listener para ajustar o `padding` da view principal com base nas barras do sistema. Isso evita que elementos fiquem escondidos atrás da status bar ou barra de navegação.

### `configuracao();`
Chama o método que conecta os componentes XML às variáveis Java.

### `eventos();`
Chama o método que registra os eventos de clique dos botões.

---

## `configuracao()`

Esse método usa `findViewById(...)` para localizar cada elemento da tela pelo `id` definido no XML.

Exemplo conceitual:

- `findViewById(R.id.edtPrimeiroNumero)` localiza o campo do primeiro número.
- `findViewById(R.id.btnSomar)` localiza o botão de soma.
- `findViewById(R.id.txvResultado)` localiza a área onde o resultado será exibido.

Sem essa etapa, a activity não conseguiria ler os dados digitados nem responder aos cliques.

---

## `eventos()`

Esse método registra um `setOnClickListener(...)` para cada botão.

### Botão Somar
Ao clicar, chama `executar("+")`.

### Botão Subtrair
Ao clicar, chama `executar("-")`.

### Botão Multiplicar
Ao clicar, chama `executar("*")`.

### Botão Dividir
Ao clicar, chama `executar("/")`.

Ou seja, todos os botões reutilizam o mesmo método de cálculo e mudam apenas o operador enviado como parâmetro. Isso reduz repetição de código.

---

## `executar(String op)`

Esse método centraliza a lógica da calculadora.

### Etapa 1: bloco `try`
O código entra em um bloco protegido para evitar que erros de conversão ou entrada inválida quebrem o app.

### Etapa 2: criação das variáveis numéricas
São criadas três variáveis:

- `n1`: primeiro número
- `n2`: segundo número
- `resultado`: valor calculado

### Etapa 3: leitura dos campos
O conteúdo de cada `EditText` é lido com `getText().toString()`.

### Etapa 4: conversão
`Double.parseDouble(...)` converte o texto em número decimal.

### Etapa 5: decisão pela operação
O método compara o parâmetro `op`:

- `"+"` → soma
- `"-"` → subtração
- `"*"` → multiplicação
- `"/"` → divisão

### Etapa 6: tratamento especial da divisão
Se `n2 == 0`, o app mostra uma mensagem de erro obtida por recurso de string (`msg_divisao_zero`), mantendo a tela compatível com a internacionalização.

Se o divisor for válido, a divisão é executada e o resultado é exibido.

### Etapa 7: exibição do resultado
`txvResultado.setText(...)` atualiza a interface com o valor final.

### Etapa 8: tratamento de erro
Se o usuário deixar um campo vazio ou digitar algo inválido, o `catch` exibe outra mensagem obtida por recurso de string (`msg_erro_calculo`).

---

## 5. Fluxo de dados do aplicativo

O fluxo de dados é simples e linear:

1. **Entrada do usuário**  
   O usuário digita dados em `edtPrimeiroNumero` e `edtSegundoNumero`.

2. **Geração do evento**  
   O clique em um botão gera um evento `onClick`.

3. **Envio do comando**  
   O listener envia um operador (`+`, `-`, `*`, `/`) para `executar(op)`.

4. **Processamento**  
   A activity lê os textos, converte para `Double` e realiza a conta.

5. **Saída**  
   O resultado é escrito em `txvResultado`.

Em outras palavras, o dado sai da interface, passa pela lógica da activity e volta para a interface já transformado em resultado.

---

## 6. Eventos do aplicativo

Neste projeto, o principal evento de interação é o **clique em botão**.

### Evento de clique
Cada botão possui um `OnClickListener`, que é disparado quando o usuário toca no componente.

Fluxo do evento:

1. usuário toca no botão
2. Android dispara `onClick(View v)`
3. o listener chama `executar(op)`
4. a interface é atualizada com `setText(...)`

### Eventos indiretos importantes
Mesmo sem código explícito para todos eles, o Android também gerencia:

- criação da activity
- destruição da activity
- recriação ao girar a tela
- pausa quando o usuário minimiza o app
- retomada quando o usuário volta ao app

---

## 7. Ciclo de vida completo da Activity no Android

Toda activity segue um ciclo de vida controlado pelo sistema operacional.

### `onCreate()`
Chamado quando a activity é criada.

Neste projeto, é aqui que a interface é carregada e os eventos são configurados.

### `onStart()`
Chamado quando a activity se torna visível.

Mesmo sem sobrescrita no código, esse passo acontece automaticamente.

### `onResume()`
Chamado quando a activity fica pronta para interação com o usuário.

É o estado em que a calculadora normalmente permanece enquanto o usuário está usando a tela.

### `onPause()`
Chamado quando a activity perde foco parcial.

Exemplo: abrir uma janela por cima ou trocar temporariamente de app.

### `onStop()`
Chamado quando a activity deixa de estar visível.

Exemplo: usuário sai completamente da tela.

### `onRestart()`
Chamado quando a activity que estava parada volta a ser preparada para uso.

### `onDestroy()`
Chamado quando a activity é destruída.

Isso pode acontecer ao fechar a tela ou durante recriações controladas pelo sistema.

### Ciclo prático neste projeto

Um fluxo comum seria:

1. app abre → `onCreate()`
2. tela aparece → `onStart()`
3. usuário interage → `onResume()`
4. usuário gira o celular → activity atual pode passar por `onPause()`, `onStop()`, `onDestroy()`
5. nova instância é criada → `onCreate()`, `onStart()`, `onResume()`

Como o projeto não implementa salvamento manual de estado de negócio, ele depende principalmente do mecanismo automático de restauração das próprias views do Android durante recriações. Em geral, componentes com `id` estável, como `EditText` e `TextView`, podem recuperar seu conteúdo visual; já estados mais elaborados, derivados ou controlados fora das views precisariam de tratamento explícito se o projeto quisesse garantir sua preservação.

---

## 8. Acoplamentos das telas em vertical e horizontal

O projeto não possui várias activities acopladas entre si. Em vez disso, ele possui **uma única activity** com **duas versões de layout**, uma para vertical e outra para horizontal.

### Acoplamento vertical

O arquivo `res/layout/activity_main.xml` é a versão padrão da tela.

Nele, a organização principal é vertical:

- textos explicativos
- campos de entrada
- linha de botões 1
- linha de botões 2
- resultado

Isso cria um fluxo de leitura de cima para baixo.

### Acoplamento horizontal

O arquivo `res/layout-land/activity_main.xml` é usado quando o aparelho está em modo paisagem.

Nesse caso, a tela fica dividida em duas colunas:

- coluna da esquerda: entradas
- coluna da direita: botões e resultado

### Como esse acoplamento funciona

O acoplamento entre código e layout ocorre por meio dos mesmos `ids`:

- `edtPrimeiroNumero`
- `edtSegundonumero` (id real definido no XML)
- `btnSomar`
- `btnSubtrair`
- `btnMultiplicar`
- `btnDividir`
- `txvResultado`

Isso é importante porque a `MainActivity` chama sempre `setContentView(R.layout.activity_main)`, e o Android escolhe automaticamente a versão correta do arquivo com base na orientação da tela.

Assim, a lógica Java não precisa mudar. O que muda é apenas a disposição visual dos componentes.

### Resumo do acoplamento

- **Acoplamento forte entre activity e ids dos componentes**: a lógica depende desses identificadores existirem em ambos os layouts.
- **Acoplamento fraco entre orientação e lógica**: a regra de cálculo não depende da posição visual dos elementos.
- **Acoplamento por contrato de recurso**: os dois XMLs precisam manter os mesmos ids para que a mesma activity funcione nas duas orientações.

---

## 9. Internacionalização (i18n)

O projeto usa recursos de string em XML, o que é a abordagem correta no Android para internacionalização.

### Onde isso aparece

- `res/values/strings.xml`
- `res/values-en-rCA/strings.xml`

Os textos dos componentes são referenciados com `@string/...`, por exemplo:

- `@string/DigitePrimeiroNumero`
- `@string/Somar`
- `@string/Resultado`

Isso evita texto fixo direto no layout e facilita tradução.

## Como o Android resolve o idioma

O Android procura o recurso mais específico compatível com o idioma/região do aparelho. Se não encontrar, usa o diretório padrão `values/`.

## Convenção de organização dos idiomas

Em Android, a convenção é:

- `values/` guarda o conjunto padrão de strings
- diretórios qualificados, como `values-en-rCA/`, guardam variações para idioma e região específicos

Didaticamente, esse projeto mostra corretamente o mecanismo: a interface usa chaves `@string/...`, e o Android decide em tempo de execução qual arquivo de recursos carregar.

### Organização atual do projeto

No estado atual do app:

- `values/strings.xml` funciona como conjunto padrão e contém os textos em português
- `values-en-rCA/strings.xml` contém a variação em inglês para a localidade `en-CA`

Isso deixa a internacionalização coerente com a convenção do Android: a interface padrão do projeto fica em português, e aparelhos configurados para `en-CA` recebem as traduções em inglês.

### Leitura didática dessa estrutura

Além disso, as mensagens de erro visíveis ao usuário também foram colocadas em recursos de string, mantendo o mesmo padrão de internacionalização usado pelos rótulos da interface.

Independentemente do idioma específico salvo em cada arquivo, o ponto principal para estudo é este:

- o layout referencia textos por chave, usando `@string/...`
- os textos ficam separados da lógica Java
- o Android seleciona o conjunto de recursos mais adequado para idioma e localidade

Assim, o projeto demonstra bem o princípio técnico da internacionalização: separar textos da lógica e da interface.

---

## 10. Manifest e inicialização do app

No `AndroidManifest.xml`, a activity declarada é:

- `.MainActivity`

Ela possui um `intent-filter` com:

- `android.intent.action.MAIN`
- `android.intent.category.LAUNCHER`

Isso significa que ela é a porta de entrada do aplicativo, ou seja, a primeira tela aberta quando o usuário toca no ícone.

### Outros atributos relevantes

- `android:theme="@style/Theme.CalculadoraNoite"`: tema visual do app
- `android:supportsRtl="true"`: permite suporte a interfaces da direita para a esquerda
- `android:windowSoftInputMode="adjustResize"`: redimensiona a área visível quando o teclado aparece

---

## 11. Organização visual da interface

### Layout padrão (`activity_main.xml`)

Componentes principais:

- `ConstraintLayout` como contêiner raiz
- `ScrollView` para permitir rolagem
- `LinearLayout` vertical para organizar o conteúdo
- dois `TextView` de orientação
- dois `EditText` para entrada numérica
- quatro `Button` para operações
- um `TextView` para o resultado

### Layout paisagem (`layout-land/activity_main.xml`)

Mantém os mesmos componentes, mas reorganiza a tela em duas colunas. Isso melhora o aproveitamento do espaço horizontal.

---

## 12. Dependências e configuração do build

No módulo `app`, o projeto usa:

- `appcompat`
- `material`
- `activity-ktx`
- `constraintlayout`
- `junit`
- `androidx.test.ext:junit`
- `espresso-core`

### Configurações principais

Os valores exatos de `compileSdk`, `minSdk`, `targetSdk`, `versionCode` e `versionName` ficam centralizados em `Calculadora/app/build.gradle.kts`.

Para um README didático, o mais importante é entender o papel dessas propriedades:

- `compileSdk`: define contra qual API o app é compilado
- `minSdk`: define a versão mínima do Android suportada
- `targetSdk`: informa para qual comportamento moderno do Android o app foi ajustado
- `versionCode` e `versionName`: identificam a versão técnica e a versão visível do app

Isso mostra que o projeto segue a organização padrão de build do Android Gradle Plugin.

---

## 13. Resumo técnico do fluxo completo

### Fluxo de inicialização
1. o sistema lê o `Manifest`
2. encontra a `MainActivity` como launcher
3. cria a activity
4. executa `onCreate()`
5. carrega o layout adequado à orientação
6. associa views com `findViewById`
7. registra listeners

### Fluxo de uso
1. usuário digita os números
2. usuário toca em uma operação
3. listener chama `executar(op)`
4. valores são convertidos
5. operação é realizada
6. resultado aparece na tela

### Fluxo de rotação
1. orientação muda
2. Android escolhe outro layout compatível
3. a activity pode ser recriada
4. a lógica continua a mesma porque os ids foram mantidos

---

## 14. Pontos didáticos mais importantes deste projeto

Este projeto é bom para estudar:

- ligação entre XML e Java com `findViewById`
- tratamento de eventos com `OnClickListener`
- leitura de entrada com `EditText`
- atualização da interface com `TextView`
- tratamento de exceções com `try/catch`
- layouts alternativos por orientação
- separação de textos para internacionalização
- papel do `Manifest`
- ciclo de vida básico de uma `Activity`

---

## 15. Conclusão

A `CalculadoraNoite` é um projeto Android simples, mas muito útil para aprendizado. Ele mostra de forma prática como uma activity controla uma interface, como os eventos do usuário disparam regras de negócio, como os dados percorrem a aplicação e como o Android troca layouts conforme a orientação da tela.

Além disso, o projeto já apresenta conceitos importantes de desenvolvimento Android real, como internacionalização, separação de recursos, tema visual, manifesto da aplicação e integração com o ciclo de vida da activity.
