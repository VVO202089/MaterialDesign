package com.example.materialdesign

import androidx.room.Room
import com.example.materialdesign.recyclerview.fragment.editNotes.EditNoteViewModel
import com.example.materialdesign.recyclerview.fragment.listNotesAndAffair.RecyclerViewViewModel
import com.example.materialdesign.recyclerview.model.NotesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = Application()

val appModule = module {

    single<DataBase> {
        Room.databaseBuilder(get(), DataBase::class.java, "notes_database").allowMainThreadQueries().build()
    }
    single<NoteDao> { get<DataBase>().noteDao() }

    single<NotesRepository> {
        NotesRepository(noteDao = get())
    }

    viewModel<RecyclerViewViewModel>{
        RecyclerViewViewModel(get(),androidContext())
    }

    viewModel<EditNoteViewModel> {noteID->
        EditNoteViewModel(noteID.get(),get())
    }

}
