package network;

public interface ICategoryRemoteDataSource {
    void makeNetworkCallBack(NetworkCallBack networkCallBack);

    void makeRandomMeal(NetworkCallBack networkCallBack);
    public void makeAreaCall(NetworkCallBack networkCallBack);
    public void makeLookUpMealByIDCall(NetworkCallBack networkCallBack ,String id);

    public void makeFilterByCategoryCall (NetworkCallBack networkCallBack,String name);

    public void makeSearchByNameCall(NetworkCallBack networkCallBack,String name);
}
