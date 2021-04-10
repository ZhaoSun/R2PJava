package begin;

//SystemPermission 类使用简单的条件逻辑管理访问软件系统的许可状态
//SystemPermission 中的 state 实例变量会在 request，claimed，denied 和 granted 等状态之间转换
public class SystemPermission {
//    public final static String REQUESTED = "REQUESTED";
//    public final static String CLAIMED = "CLAIMED";
//    public final static String GRANTED = "GRANTED";
//    public final static String DENIED = "DENIED";
//    public final static String UNIX_REQUESTED = "UNIX_REQUESTED";
//    public final static String UNIX_CLAIMED = "UNIX_CLAIMED";

    private SystemProfile profile;
    private SystemUser requestor;
    private SystemAdmin admin;
    private boolean isGranted;
    private boolean isUnixPermissionGranted;
    private PermissionState state;

    public SystemPermission(SystemUser requestor, SystemProfile profile) {
        this.requestor = requestor;
        this.profile = profile;
        state = PermissionState.REQUESTED;
        isGranted = false;
        notifyAdminOfPermissionRequest();
    }

    public void claimedBy(SystemAdmin admin) {
        state.claimedBy(admin, this);
    }

    public void deniedBy(SystemAdmin admin) {
        state.deniedBy(admin, this);
    }

    public void grantedBy(SystemAdmin admin) {
        state.grantedBy(admin, this);
    }

    private void notifyAdminOfPermissionRequest() {
        System.out.println("Permission Requested.");
    }

    void willBeHandledBy(SystemAdmin admin) {
        this.admin = admin;
        System.out.println("Permission Claimed.");
    }

    void notifyUnixAdminsOfPermissionRequest() {
        System.out.println("UNIX Permission Requested.");
    }

    void notifyUserOfPermissionRequestResult() {
        if (isGranted)
            System.out.println("Permission Granted.");
        else
            System.out.println("Permission NOT Granted.");
    }

    public String state() {
        return state.toString();
    }

    public boolean isGranted() {
        return isGranted;
    }

    public boolean isUnixPermissionGranted() {
        return isUnixPermissionGranted;
    }

    public PermissionState getState() {
        return state;
    }

    public void setState(PermissionState state) {
        this.state = state;
    }

    public SystemProfile getProfile() {
        return profile;
    }

    public void setProfile(SystemProfile profile) {
        this.profile = profile;
    }

    public SystemUser getRequestor() {
        return requestor;
    }

    public void setRequestor(SystemUser requestor) {
        this.requestor = requestor;
    }

    public SystemAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(SystemAdmin admin) {
        this.admin = admin;
    }

    public void setGranted(boolean granted) {
        isGranted = granted;
    }

    public void setUnixPermissionGranted(boolean unixPermissionGranted) {
        isUnixPermissionGranted = unixPermissionGranted;
    }
}