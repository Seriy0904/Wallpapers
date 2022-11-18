package dev.seriy0904.wallpapers.ui.latest;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dev.seriy0904.wallpapers.data.api.WallhavenApi;

public class LatestListViewModelProvider implements ViewModelProvider.Factory {
    private final WallhavenApi retrofit;
    public LatestListViewModelProvider(WallhavenApi _retrofit){
        retrofit = _retrofit;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LatestListViewModel(retrofit);
    }
}