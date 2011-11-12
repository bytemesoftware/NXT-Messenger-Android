package com.bytemesoftware.nxtmessengeradfree;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class NXTTabWidget extends TabActivity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903044);
    Resources localResources = getResources();
    TabHost localTabHost = getTabHost();
    Intent localIntent1 = new Intent().setClass(this, MessageActivity.class);
    localTabHost.addTab(localTabHost.newTabSpec("message").setIndicator("Message", localResources.getDrawable(2130837515)).setContent(localIntent1));
    Intent localIntent2 = new Intent().setClass(this, CommandActivity.class);
    localTabHost.addTab(localTabHost.newTabSpec("command").setIndicator("Commands", localResources.getDrawable(2130837514)).setContent(localIntent2));
    Intent localIntent3 = new Intent().setClass(this, MoveActivity.class);
    localTabHost.addTab(localTabHost.newTabSpec("move").setIndicator("Move", localResources.getDrawable(2130837516)).setContent(localIntent3));
    localTabHost.setCurrentTabByTag("message");
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.NXTTabWidget
 * JD-Core Version:    0.6.0
 */