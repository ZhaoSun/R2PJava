package end;

public class PermissionState {
    private String name;

    public PermissionState(String name) {
        this.name = name;
    }

//    public final static PermissionState REQUESTED = new PermissionState("REQUESTED");
//    public final static PermissionState CLAIMED = new PermissionState("CLAIMED");
//    public final static PermissionState GRANTED = new PermissionState("GRANTED");
//    public final static PermissionState DENIED = new PermissionState("DENIED");
//    public final static PermissionState UNIX_REQUESTED = new PermissionState("UNIX_REQUESTED");
//    public final static PermissionState UNIX_CLAIMED = new PermissionState("UNIX_CLAIMED");

    public final static PermissionState REQUESTED = new PermissionRequested();
    public final static PermissionState CLAIMED = new PermissionClaimed();
    public final static PermissionState GRANTED = new PermissionGranted();
    public final static PermissionState DENIED = new PermissionDenied();
    public final static PermissionState UNIX_REQUESTED = new UnixPermissionRequested();
    public final static PermissionState UNIX_CLAIMED = new UnixPermissionClaimed();

    @Override
    public String toString() {
        return name;
    }

    public void claimedBy(SystemAdmin admin, SystemPermission permission) {

    }

    public void deniedBy(SystemAdmin admin, SystemPermission permission) {

    }

    public void grantedBy(SystemAdmin admin, SystemPermission permission) {

    }
}
