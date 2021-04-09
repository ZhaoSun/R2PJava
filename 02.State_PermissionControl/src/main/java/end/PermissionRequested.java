package end;

public class PermissionRequested extends PermissionState{
    public PermissionRequested() {
        super("REQUESTED");
    }

    @Override
    public void claimedBy(SystemAdmin admin, SystemPermission permission) {
        permission.willBeHandledBy(admin);
        permission.setState(PermissionState.CLAIMED);
    }
}
