package dev.seriy0904.wallpapers.ui.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dev.seriy0904.wallpapers.data.api.WallhavenApi;
import dev.seriy0904.wallpapers.ui.viewModel.ListViewModel;

public class ListViewModelProvider implements ViewModelProvider.Factory {
    private final WallhavenApi retrofit;
    public ListViewModelProvider(WallhavenApi _retrofit){
        retrofit = _retrofit;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListViewModel(retrofit);
    }
}
