package github.encapsulation.ormlite;

import android.content.Context;

public class _Test {

    public void test(Context context){
        //用法
        TDao<User> tdao = new TDao<User>(context, User.class);

        //直接调用查询
        tdao.queryAll();

    }
}
