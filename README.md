# 📏 ImcApp

O **ImcApp** é um aplicativo Android desenvolvido para calcular o Índice de Massa Corporal (IMC) de forma prática e intuitiva, ajudando os usuários a monitorarem sua saúde corporal. O aplicativo permite que o usuário insira seu peso e altura, calcula o IMC e exibe o resultado com uma classificação correspondente (ex.: abaixo do peso, peso normal, sobrepeso, etc.).

## 🚀 Funcionalidades

- **Cálculo de IMC**: Insira peso (kg) e altura (m) para calcular o IMC.
- **Classificação do IMC**: Exibe a categoria do IMC com base nos padrões da Organização Mundial da Saúde (OMS).
- **Histórico de Cálculos**: Armazena os cálculos realizados em um banco de dados SQLite, exibidos em uma lista com `RecyclerView`.
- **Interface Intuitiva**: Design simples e amigável para facilitar o uso por diferentes faixas etárias.

## 🧠 Tecnologias e Componentes Aplicados

| Componente/Tecnologia | Utilização |
|-----------------------|------------|
| `RecyclerView`        | Exibição do histórico de cálculos de IMC |
| `SQLite`              | Armazenamento local dos cálculos realizados |
| `Activities`          | Telas para entrada de dados, resultado e histórico |
| Kotlin                | Linguagem principal para lógica e interface |
| XML Layouts           | Design das telas com cores personalizadas |

### Banco de Dados

Tabela `imc_history` no SQLite:

| Campo     | Tipo                     |
|-----------|--------------------------|
| `id`      | INTEGER PRIMARY KEY AUTOINCREMENT |
| `peso`    | REAL                     |
| `altura`  | REAL                     |
| `imc`     | REAL                     |
| `classificacao` | TEXT               |
| `data`    | TEXT (ex.: "2025-06-17 08:25") |

## 🛠️ Tecnologias

- **Linguagem**: Kotlin
- **Plataforma**: Android
- **IDE Recomendada**: Android Studio
- **Dependências**: Bibliotecas padrão do Android (RecyclerView, SQLite)
- **Cores**:
  - Fundo: `#FFFFFF` (branco)
  - Títulos: `#0288D1` (azul escuro)
  - Botões: `#4FC3F7` (azul claro)
  - Texto: `#333333` (cinza escuro), `#FFFFFF` (branco)

## 🧪 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/CamilaStolte/ImcApp.git
   ```
2. Abra o projeto no Android Studio.
3. Sincronize com o Gradle (`Sync Project with Gradle Files`).
4. Configure um dispositivo Android ou emulador.
5. Execute o aplicativo clicando em `Run`.

## 📁 Estrutura de Diretórios

```
ImcApp/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/imcapp/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── ResultadoActivity.kt
│   │   │   │   ├── HistoricoActivity.kt
│   │   │   │   └── db/
│   │   │   │       └── DatabaseHelper.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_resultado.xml
│   │   │   │   │   ├── activity_historico.xml
│   │   │   │   │   └── item_imc.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── strings.xml
│   │   │   └── AndroidManifest.xml
│
├── gradle/
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
├── README.md
```

## 📅 Histórico de Desenvolvimento

- Inicialização do repositório e configuração do projeto Android
- Implementação da `MainActivity` para entrada de peso e altura
