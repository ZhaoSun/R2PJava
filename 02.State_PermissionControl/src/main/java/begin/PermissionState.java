package begin;

public class PermissionState {

    public final static PermissionState REQUESTED = new PermissionRequested();
    public final static PermissionState CLAIMED = new PermissionClaimed();
    public final static PermissionState GRANTED = new PermissionGranted();
    public final static PermissionState DENIED = new PermissionDenied();
    public final static PermissionState UNIX_REQUESTED = new UnixPermissionRequested();
    public final static PermissionState UNIX_CLAIMED = new UnixPermissionClaimed();

    private final String name;

    public PermissionState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void claimedBy(SystemAdmin admin, SystemPermission permission) {
        if (!permission.getState().equals(PermissionState.REQUESTED) && !permission.getState().equals(PermissionState.UNIX_REQUESTED))
            return;
        permission.willBeHandledBy(admin);
        if (permission.getState().equals(PermissionState.REQUESTED))
            permission.setState(PermissionState.CLAIMED);
        else if (permission.getState().equals(PermissionState.UNIX_REQUESTED))
            permission.setState(PermissionState.UNIX_CLAIMED);
    }

    public void deniedBy(SystemAdmin admin, SystemPermission permission) {
        if (!permission.getState().equals(PermissionState.CLAIMED) && !permission.getState().equals(PermissionState.UNIX_CLAIMED))
            return;
        if (!permission.getAdmin().equals(admin))
            return;
        permission.setGranted(false);
        permission.setUnixPermissionGranted(false);
        permission.setState(PermissionState.DENIED);
        permission.notifyUserOfPermissionRequestResult();
    }

    public void grantedBy(SystemAdmin admin, SystemPermission permission) {
        if (!permission.getState().equals(PermissionState.CLAIMED) && !permission.getState().equals(PermissionState.UNIX_CLAIMED))
            return;
        if (!permission.getAdmin().equals(admin))
            return;

        if (permission.getProfile().isUnixPermissionRequired() && permission.getState().equals(PermissionState.UNIX_CLAIMED))
            permission.setUnixPermissionGranted(true);
        else if (permission.getProfile().isUnixPermissionRequired() && !permission.isUnixPermissionGranted()) {
            permission.setState(PermissionState.UNIX_REQUESTED);
            permission.notifyUnixAdminsOfPermissionRequest();
            return;
        }
        permission.setState(PermissionState.GRANTED);
        permission.setGranted(true);
        permission.notifyUserOfPermissionRequestResult();
    }

}
