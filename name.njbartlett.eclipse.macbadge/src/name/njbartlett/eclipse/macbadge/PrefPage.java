
package name.njbartlett.eclipse.macbadge;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * @author Neil Bartlett
 * 
 */
public class PrefPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{

	private final IPreferenceStore prefStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, "name.njbartlett.eclipse.macbadge");

	@Override
	protected void createFieldEditors()
	{
		StringFieldEditor editor = new StringFieldEditor("name", "Badge Title:", 10, getFieldEditorParent());
		addField(editor);
	}

	@Override
	protected IPreferenceStore doGetPreferenceStore()
	{
		return prefStore;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
	 */
	@Override
	public boolean performOk()
	{
		boolean result = super.performOk();
		Startup.updateBadge(Startup.createBadgeText());
		return result;
	}

	public void init(IWorkbench workbench)
	{}

}
