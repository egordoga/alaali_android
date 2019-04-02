package ua.alaali.viewModel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import ua.alaali.model.Group;
import ua.alaali.model.Product;
import ua.alaali.repo.ApiCall;

public class AppViewModel extends AndroidViewModel {
    public List<Group> groupList;
    public List<Product> productList;
    private ApiCall apiCall;

    public AppViewModel(@NonNull Application application) {
        super(application);

        apiCall = new ApiCall();
    }

    public void setProductListBySectionId(long id) {
        productList = apiCall.getAllProductBySectionId(id);
    }

    public void setProductListAll() {
        productList = apiCall.getAllProduct();
    }

    public void setGroupListAll() {
        if (groupList == null) {
            groupList = apiCall.getAllGroup();
        }


    }

    public Product getOne(long id) {
        return apiCall.getProductById(id);
    }
}
