# NotesApp 📚
**Autor:** Naythan Villafuerte

Aplicación móvil nativa Android para la gestión de notas personales, desarrollada en Kotlin utilizando Jetpack Compose y Room.

---

## Funcionamiento 🚀

- Permite crear, editar, eliminar y visualizar notas.
- Cada nota puede tener título, contenido, color personalizado y fecha de creación/modificación.
- Las notas se almacenan localmente en una base de datos SQLite usando Room.
- Se pueden filtrar notas por color o buscar por texto en la pantalla principal.
- Se puede cambiar el tema visual de la aplicación (Claro, Oscuro o VIU).
- La navegación entre pantallas se realiza usando Navigation Compose.

### Vide de funcionamiento
###
###
https://github.com/user-attachments/assets/f9907c9f-4c5b-488d-ba7e-8e0fe713d9ab

---


## Tecnologías y Herramientas Utilizadas 🛠

- **Kotlin** como lenguaje principal.
- **Jetpack Compose** para construir interfaces de usuario de forma declarativa.
- **Room (SQLite)** para la persistencia local de datos.
- **StateFlow** para gestionar el estado de forma reactiva.
- **MVVM** como patrón de arquitectura.
- **Git y GitHub** para control de versiones.

---

## Estructura General 📁
```bash
com/example/notesapp/
├── data/
│   ├── local/
│   │   ├── NoteDao.kt
│   │   └── NoteDatabase.kt
│   │   └── NoteDatabaseProvider.kt
│   ├── model/
│   │   └── Note.kt
│   └── repository/
│       └── NoteRepository.kt
│
├── ui/
│   ├── components/
│   │   ├── NoteCard.kt   
│   │   └── CustomButton.kt
│   │   ├── NoteSection.kt   
│   │   └── SectionLabel.kt
│   └── screens/
│       ├── home/
│       │   ├── HomeScreen.kt
│       │   └── HomeViewModel.kt
│       │   └── HomeViewModelFactory.kt
│       ├── add_edit/
│       │   ├── AddEditScreen.kt
│       │   └── AddEditViewModel.kt
│       │   └── AddEditViewModelFactory.kt
│       └── detail/
│           ├── DetailScreen.kt
│           └── DetailViewModel.kt
│           └── DetailViewModelFactory.kt
│
├── navigation/
│   └── AppNavigation.kt    
│
├── theme/
│   ├── Color.kt
│   ├── Theme.kt
│   ├── ThemeViewModel.kt
│   └── Type.kt
│
├── MainActivity.kt         
└── NotesApp.kt   
```

## Instalación 📲

1. Clonar el repositorio:

```bash
git clone https://github.com/nayvilla/notesApp.git
```
