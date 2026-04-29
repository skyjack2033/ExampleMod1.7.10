package appeng.me.helpers;

import appeng.api.networking.IGridNode;
import appeng.api.util.AEPartLocation;

public interface IGridProxyable {
    IGridNode getGridNode(AEPartLocation dir);
    AENetworkProxy getProxy();
}
