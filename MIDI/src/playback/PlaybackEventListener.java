package playback;

public interface PlaybackEventListener {

	public void playbackStarted(PlaybackEventData playbackEventData);
	
	public void playbackStopped(PlaybackEventData playbackEventData);

	public void voiceRemoved(PlaybackEventData playbackEventData);
}