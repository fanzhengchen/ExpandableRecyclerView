package itemhelper;

/**
 * Created by fanzhengchen on 8/3/16.
 */
public interface ItemTouchHelperAdapter {

    public boolean onItemMove(int fromPosition, int toPosition, boolean isParent);

    public void onItemDismiss(int position);

    public boolean allCollapsed();
}
