package mx.edu.utng.appdiario.audio

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AudioManager(private val context: Context) {
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var audioFile: File? = null
    private var isRecording = false
    private var isPlaying = false

    fun startRecording(): String? {
        return try {
            // Crear directorio interno seguro
            val storageDir = File(context.cacheDir, "audio_records")
            storageDir.mkdirs()

            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            audioFile = File.createTempFile("AUDIO_${timeStamp}_", ".m4a", storageDir)

            mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                MediaRecorder(context)
            } else {
                @Suppress("DEPRECATION")
                MediaRecorder()
            }

            mediaRecorder?.apply {
                try {
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                    setOutputFile(audioFile?.absolutePath)
                    setAudioSamplingRate(44100)
                    setAudioEncodingBitRate(128000)
                    setAudioChannels(1)

                    prepare()
                    start()
                    isRecording = true
                    Log.d("AudioManager", "✅ Grabación iniciada. Archivo: ${audioFile?.absolutePath}")
                    return audioFile?.absolutePath
                } catch (e: IOException) {
                    Log.e("AudioManager", "❌ Error al preparar o iniciar grabación: ${e.message}")
                    e.printStackTrace()
                    release()
                    mediaRecorder = null
                    return null
                }
            }
            null
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error general en startRecording: ${e.message}")
            e.printStackTrace()
            null
        }
    }

    fun stopRecording() {
        try {
            if (isRecording && mediaRecorder != null) {
                mediaRecorder?.apply {
                    try {
                        stop()
                    } catch (e: RuntimeException) {
                        Log.e("AudioManager", "❌ Error al detener (grabación muy corta): ${e.message}")
                        audioFile?.delete()
                        audioFile = null
                    }
                    release()
                }
                mediaRecorder = null
                isRecording = false

                val fileExists = audioFile?.exists() ?: false
                val fileSize = audioFile?.length() ?: 0
                Log.d("AudioManager", "⏹️ Grabación detenida. Existe: $fileExists, tamaño: $fileSize bytes")

                if (fileSize < 1000) {
                    Log.w("AudioManager", "⚠️ Archivo muy pequeño, posible grabación corrupta")
                }
            }
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error al detener grabación: ${e.message}")
            e.printStackTrace()
        }
    }

    // MÉTODO ORIGINAL - reproduce solo el archivo grabado
    fun startPlaying(onCompletion: () -> Unit = {}) {
        audioFile?.let { file ->
            try {
                stopPlaying()

                Log.d("AudioManager", "🔍 Verificando archivo: ${file.absolutePath}")
                Log.d("AudioManager", "   - Existe: ${file.exists()}")
                Log.d("AudioManager", "   - Tamaño: ${file.length()} bytes")
                Log.d("AudioManager", "   - Lectura: ${file.canRead()}")

                if (file.exists() && file.length() > 1000) {
                    mediaPlayer = MediaPlayer().apply {
                        setDataSource(file.absolutePath)
                        setOnPreparedListener {
                            start()
                            this@AudioManager.isPlaying = true
                            Log.d("AudioManager", "▶️ Reproducción iniciada")
                        }
                        setOnCompletionListener {
                            stopPlaying()
                            onCompletion()
                            Log.d("AudioManager", "✅ Reproducción finalizada")
                        }
                        setOnErrorListener { _, what, extra ->
                            Log.e("AudioManager", "❌ Error en reproducción: what=$what extra=$extra")
                            stopPlaying()
                            onCompletion()
                            true
                        }
                        prepareAsync()
                    }
                } else {
                    Log.e("AudioManager", "❌ Archivo inexistente o corrupto")
                    onCompletion()
                }
            } catch (e: Exception) {
                Log.e("AudioManager", "❌ Error al iniciar reproducción: ${e.message}")
                e.printStackTrace()
                stopPlaying()
                onCompletion()
            }
        } ?: run {
            Log.e("AudioManager", "❌ No hay archivo para reproducir")
            onCompletion()
        }
    }

    // NUEVO MÉTODO - reproduce cualquier archivo por su ruta
    fun startPlaying(audioFilePath: String, onCompletion: () -> Unit = {}) {
        try {
            stopPlaying()

            val file = File(audioFilePath)
            Log.d("AudioManager", "🔍 Verificando archivo para reproducción: $audioFilePath")
            Log.d("AudioManager", "   - Existe: ${file.exists()}")
            Log.d("AudioManager", "   - Tamaño: ${file.length()} bytes")
            Log.d("AudioManager", "   - Lectura: ${file.canRead()}")

            if (file.exists() && file.length() > 1000) {
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(audioFilePath)
                    setOnPreparedListener {
                        start()
                        this@AudioManager.isPlaying = true
                        Log.d("AudioManager", "▶️ Reproducción iniciada: $audioFilePath")
                    }
                    setOnCompletionListener {
                        stopPlaying()
                        onCompletion()
                        Log.d("AudioManager", "✅ Reproducción finalizada: $audioFilePath")
                    }
                    setOnErrorListener { _, what, extra ->
                        Log.e("AudioManager", "❌ Error en reproducción: what=$what extra=$extra")
                        stopPlaying()
                        onCompletion()
                        true
                    }
                    prepareAsync()
                }
            } else {
                Log.e("AudioManager", "❌ Archivo inexistente o corrupto: $audioFilePath")
                onCompletion()
            }
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error al iniciar reproducción: ${e.message}")
            e.printStackTrace()
            stopPlaying()
            onCompletion()
        }
    }

    fun stopPlaying() {
        try {
            mediaPlayer?.apply {
                if (isPlaying) {
                    stop()
                    Log.d("AudioManager", "⏹️ Reproducción detenida")
                }
                release()
            }
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error al detener reproducción: ${e.message}")
            e.printStackTrace()
        } finally {
            mediaPlayer = null
            isPlaying = false
        }
    }

    // NUEVO MÉTODO - obtener duración de un archivo de audio
    fun getAudioDuration(audioFilePath: String): Int {
        return try {
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(audioFilePath)
            mediaPlayer.prepare()
            val duration = mediaPlayer.duration / 1000 // convertir a segundos
            mediaPlayer.release()
            Log.d("AudioManager", "⏱️ Duración del audio: ${duration}s")
            duration
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error obteniendo duración: ${e.message}")
            0
        }
    }

    // NUEVO MÉTODO - verificar si un archivo es reproducible
    fun isAudioFilePlayable(audioFilePath: String): Boolean {
        return try {
            val file = File(audioFilePath)
            val exists = file.exists()
            val size = file.length()
            val playable = exists && size > 1000

            Log.d("AudioManager", "🔍 Verificando archivo: $audioFilePath")
            Log.d("AudioManager", "   - Existe: $exists")
            Log.d("AudioManager", "   - Tamaño: $size bytes")
            Log.d("AudioManager", "   - Reproducible: $playable")

            playable
        } catch (e: Exception) {
            Log.e("AudioManager", "❌ Error verificando archivo: ${e.message}")
            false
        }
    }

    fun getRecordingState(): Boolean = isRecording
    fun getPlayingState(): Boolean = isPlaying
    fun getAudioFilePath(): String? = audioFile?.absolutePath

    fun deleteRecording() {
        stopRecording()
        stopPlaying()
        audioFile?.delete()
        audioFile = null
        Log.d("AudioManager", "🗑️ Grabación eliminada")
    }

    fun hasRecording(): Boolean {
        val exists = audioFile?.exists() == true
        val size = audioFile?.length() ?: 0
        val valid = exists && size > 1000

        Log.d("AudioManager", "📊 hasRecording: $valid (existe=$exists tamaño=$size)")
        return valid
    }

    fun cleanup() {
        stopRecording()
        stopPlaying()
        Log.d("AudioManager", "🧹 Cleanup completado")
    }
}