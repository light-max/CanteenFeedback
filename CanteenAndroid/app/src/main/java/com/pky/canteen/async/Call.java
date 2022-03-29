package com.pky.canteen.async;

public interface Call {
    interface OnReturnData<T> {
        void onSuccess(T data);
    }

    interface OnSuccess extends Runnable {
    }

    interface OnError {
        void onError(String message, Exception e);
    }

    interface OnBefore extends Runnable {
    }

    interface OnAfter extends Runnable {
    }
}
