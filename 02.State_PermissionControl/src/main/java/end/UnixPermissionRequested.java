package end;

public class UnixPermissionRequested extends PermissionState{
    public UnixPermissionRequested() {
        super("UNIX_REQUESTED");
    }

    @Override
    public void claimedBy(SystemAdmin admin, SystemPermission permission) {
        permission.willBeHandledBy(admin);
        permission.setState(PermissionState.UNIX_CLAIMED);
    }
}
