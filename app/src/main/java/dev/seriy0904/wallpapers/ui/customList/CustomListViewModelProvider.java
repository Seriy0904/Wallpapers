package dev.seriy0904.wallpapers.ui.customList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dev.seriy0904.wallpapers.data.api.WallhavenApi;

public class CustomListViewModelProvider implements ViewModelProvider.Factory {
    private final WallhavenApi retrofit;
    public CustomListViewModelProvider(WallhavenApi _retrofit){
        retrofit = _retrofit;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CustomListViewModel(retrofit);
    }
}
