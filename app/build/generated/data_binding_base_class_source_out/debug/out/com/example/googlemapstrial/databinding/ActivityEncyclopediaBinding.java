// Generated by view binder compiler. Do not edit!
package com.example.googlemapstrial.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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

public final class ActivityEncyclopediaBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView RecyclerView;

  @NonNull
  public final Button backk;

  @NonNull
  public final TextView score;

  private ActivityEncyclopediaBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView RecyclerView, @NonNull Button backk, @NonNull TextView score) {
    this.rootView = rootView;
    this.RecyclerView = RecyclerView;
    this.backk = backk;
    this.score = score;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEncyclopediaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEncyclopediaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_encyclopedia, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEncyclopediaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.RecyclerView;
      RecyclerView RecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (RecyclerView == null) {
        break missingId;
      }

      id = R.id.backk;
      Button backk = ViewBindings.findChildViewById(rootView, id);
      if (backk == null) {
        break missingId;
      }

      id = R.id.score;
      TextView score = ViewBindings.findChildViewById(rootView, id);
      if (score == null) {
        break missingId;
      }

      return new ActivityEncyclopediaBinding((ConstraintLayout) rootView, RecyclerView, backk,
          score);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
