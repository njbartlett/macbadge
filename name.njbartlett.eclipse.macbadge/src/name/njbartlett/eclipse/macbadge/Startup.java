
package name.njbartlett.eclipse.macbadge;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TaskBar;
import org.eclipse.swt.widgets.TaskItem;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;

/**
 * @author Neil Bartlett
 * 
 */
public class Startup implements IStartup
{

	public void earlyStartup()
	{
		updateBadge(createBadgeText());
	}

	public static String createBadgeText()
	{
		IPath wslocation = ResourcesPlugin.getWorkspace().getRoot().getLocation();
		String name = wslocation.lastSegment();

		IEclipsePreferences pref = InstanceScope.INSTANCE.getNode("name.njbartlett.eclipse.macbadge");
		if (pref != null)
		{
			String prefValue = pref.get("name", null);
			if (prefValue != null)
				name = prefValue;
		}

		return name;
	}

	public static void updateBadge(final String name)
	{
		final Display display = PlatformUI.getWorkbench().getDisplay();
		Runnable update = new Runnable()
		{
			public void run()
			{
				TaskBar taskBar = display.getSystemTaskBar();
				if (taskBar != null)
				{
					TaskItem item = taskBar.getItem(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
					if (item == null)
						item = taskBar.getItem(null);
					if (item != null)
						item.setOverlayText(name);
				}
			}
		};
		if (display.getThread() == Thread.currentThread())
			update.run();
		else
			display.asyncExec(update);

	}
}
