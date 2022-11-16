package dev.seriy0904.wallpapers.ui.toplist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dev.seriy0904.wallpapers.data.api.WallhavenApi;

public class TopListViewModelProvider implements ViewModelProvider.Factory {
    private final WallhavenApi retrofit;
    public TopListViewModelProvider(WallhavenApi _retrofit){
        retrofit = _retrofit;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TopListViewModel(retrofit);
    }
}
