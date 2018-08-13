package joker.kit.base;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentJoker extends Fragment {
    private View ACEW;
    private Activity activity_Joker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (ACEW == null) {
            ACEW = inflater.inflate(R.layout.support_simple_spinner_dropdown_item, null);
            activity_Joker = getActivity();
        }

        ViewGroup parent = (ViewGroup) ACEW.getParent();

        if (parent != null) {
            parent.removeView(ACEW);
        }

        return ACEW;
    }

}
