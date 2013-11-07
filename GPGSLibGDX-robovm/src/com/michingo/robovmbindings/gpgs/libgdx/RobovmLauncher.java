package com.michingo.robovmbindings.gpgs.libgdx;

import java.util.ArrayList;

import org.robovm.cocoatouch.foundation.NSAutoreleasePool;
import org.robovm.cocoatouch.foundation.NSDictionary;
import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.cocoatouch.foundation.NSURL;
import org.robovm.cocoatouch.uikit.UIApplication;
import org.robovm.cocoatouch.uikit.UIScreen;
import org.robovm.cocoatouch.uikit.UIView;
import org.robovm.cocoatouch.uikit.UIViewController;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import org.robovm.cocoatouch.glkit.GLKViewDrawableStencilFormat;
import com.michingo.robovmbindings.gpgs.GPGToastPlacement;
import com.michingo.robovmbindings.gpp.GPPURLHandler;
import com.michingo.robovmbindings.playservices.PlayServicesManager;
import com.michingo.robovmbindings.playservices.PlayServicesManager.LoginSucceeded;

public class RobovmLauncher extends IOSApplication.Delegate implements GPGSInterface{
	private IOSApplication gdxApp;
    private PlayServicesManager manager;
    private boolean isLoggedIn = false;

    private UIViewController managerViewController;
    private UIView managerView;

    private final String clientID = "enter your client id here";
    private final String leaderboard1_ID = "xxx";
    private final String achievement1_ID = "xxx";

    @Override
    protected IOSApplication createApplication() {
            final IOSApplicationConfiguration config = new IOSApplicationConfiguration();
            config.orientationPortrait = true;
            config.orientationLandscape = false;
            config.useAccelerometer = false;
            config.useCompass = false;
            config.stencilFormat = GLKViewDrawableStencilFormat.fromValue(1);
            
            gdxApp = new IOSApplication(new MyGame(this), config);

            return gdxApp;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean didFinishLaunching(UIApplication application, NSDictionary launchOptions) {
            final boolean result = super.didFinishLaunching(application, launchOptions);

            { // GameServices manager view
                    managerView = new UIView(UIScreen.getMainScreen().getBounds());
                    managerViewController = new UIViewController();
                    managerViewController.setView(managerView);

                    gdxApp.getUIViewController().getView().addSubview(managerView);
            }

            final ArrayList<String> achievements = new ArrayList<String>();
            achievements.add(achievement1_ID);

            final ArrayList<String> leaderboards = new ArrayList<String>();
            leaderboards.add(leaderboard1_ID);

            manager = new PlayServicesManager();
            manager.setClientId(clientID);
            manager.setViewController(managerViewController);
            manager.provideAchievementIdentifiers(achievements);
            manager.provideLeaderboardIdentifiers(leaderboards);
            manager.setToastLocation(PlayServicesManager.TOAST_BOTH, GPGToastPlacement.GPGToastPlacementTop);
            manager.setUserDataToRetrieve(true, false);
            manager.setLoginCallback(loginCallback);
            manager.didFinishLaunching();

            return result;
    }

    private LoginSucceeded loginCallback = new LoginSucceeded() {
            @Override
            public void invoke() {
                    isLoggedIn = true;
            }
    };

    @Override
    public boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSObject annotation){
            return GPPURLHandler.handleURL(url, sourceApplication, annotation);
    }

    public IOSApplication getApplication(){
              return gdxApp;
    }

    public static void main(String[] args) {
            NSAutoreleasePool pool = new NSAutoreleasePool();
            UIApplication.main(args, null, RobovmLauncher.class);
            pool.drain();
    }

	@Override
	public void login() {
		manager.login();
	}
}
