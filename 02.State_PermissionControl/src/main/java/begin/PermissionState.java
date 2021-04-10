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
}
