package com.inca.lyretrospect.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inca.lyretrospect.R;


public class MyDialog extends Dialog {

	private static TextView tv_message;

	public MyDialog(Context context) {
		super(context);
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder {
		private Context context;
		private String title;
		private String message;
		private String confirmButtonText;
		private String cancelButtonText;
		private int gravity;
		private View contentView;
		private boolean cancelable;
		private OnClickListener confirmButtonClickListener;
		private OnClickListener cancelButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}public Builder setGravity(int gravity) {
			this.gravity = gravity;
			return this;
		}

		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}
		
		public void setCancelable(boolean flag) {
			this.cancelable = flag;
		}

		public Builder setConfirmButton(int confirmButtonText,
				OnClickListener listener) {
			this.confirmButtonText = (String) context
					.getText(confirmButtonText);
			this.confirmButtonClickListener = listener;
			return this;
		}

		public Builder setConfirmButton(String confirmButtonText,
				OnClickListener listener) {
			this.confirmButtonText = confirmButtonText;
			this.confirmButtonClickListener = listener;
			return this;
		}

		public Builder setCancelButton(int cancelButtonText,
				OnClickListener listener) {
			this.cancelButtonText = (String) context
					.getText(cancelButtonText);
			this.cancelButtonClickListener = listener;
			return this;
		}

		public Builder setCancelButton(String cancelButtonText,
				OnClickListener listener) {
			this.cancelButtonText = cancelButtonText;
			this.cancelButtonClickListener = listener;
			return this;
		}
		
		public MyDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			final MyDialog dialog = new MyDialog(context, R.style.MyDialog);
			dialog.setCancelable(cancelable);
			View layout = inflater.inflate(R.layout.widget_dialog_layout, null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

			
			if (title != null) {
				((TextView) layout.findViewById(R.id.title)).setText(title);
			} else {
				layout.findViewById(R.id.title).setVisibility(
						View.GONE);
			}

			if (confirmButtonText != null) {
				((Button) layout.findViewById(R.id.confirmButton))
						.setText(confirmButtonText);
				if (confirmButtonClickListener != null) {
					((Button) layout.findViewById(R.id.confirmButton))
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									confirmButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {

				layout.findViewById(R.id.confirmLaytout).setVisibility(
						View.GONE);
			}

			if (cancelButtonText != null) {
				((Button) layout.findViewById(R.id.cancelButton))
						.setText(cancelButtonText);
				if (cancelButtonClickListener != null) {
					((Button) layout.findViewById(R.id.cancelButton))
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									cancelButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {

				layout.findViewById(R.id.cancelLaytout).setVisibility(
						View.GONE);
			}

			if (message != null) {
//				((TextView) layout.findViewById(R.id.message)).setText(message);
				tv_message = (TextView) layout.findViewById(R.id.message);
				tv_message.setText(message);
				if (gravity!=0){
					tv_message.setGravity(gravity);
				}
			} else if (contentView != null) {

				((LinearLayout) layout.findViewById(R.id.content))
						.removeAllViews();
				((LinearLayout) layout.findViewById(R.id.content)).addView(
						contentView, new LayoutParams(
								LayoutParams.FILL_PARENT,
								LayoutParams.FILL_PARENT));
			}
			dialog.setContentView(layout);
			return dialog;
		}

	}
}
