// Generated by view binder compiler. Do not edit!
package com.example.googlemapstrial.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.googlemapstrial.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityGeoMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView RecyclerView;

  @NonNull
  public final Button backGo;

  @NonNull
  public final ImageView imageView3;

  private ActivityGeoMainBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView RecyclerView, @NonNull Button backGo, @NonNull ImageView imageView3) {
    this.rootView = rootView;
    this.RecyclerView = RecyclerView;
    this.backGo = backGo;
    this.imageView3 = imageView3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGeoMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGeoMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_geo_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGeoMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.RecyclerView;
      RecyclerView RecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (RecyclerView == null) {
        break missingId;
      }

      id = R.id.backGo;
      Button backGo = ViewBindings.findChildViewById(rootView, id);
      if (backGo == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      return new ActivityGeoMainBinding((ConstraintLayout) rootView, RecyclerView, backGo,
          imageView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
